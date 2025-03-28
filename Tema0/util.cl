(* Think of these as abstract classes *)
class Comparator inherits IO {
    compareTo(o1 : Object, o2 : Object) : Int { 0 };
};

class Filter {
    filter(o : Object):Bool {true};
};

(****************************
 ** Classes Filter-related **
 ****************************)
class ProductFilter inherits Filter {
    filter(o : Object) : Bool {
        case o of
            p : Product => true;
            e : Edible => true;
            s : Soda => true;
            c : Coffee => true;
            l : Laptop => true;
            r : Router => true;
            obj : Object => false;
        esac

    };
};

class RankFilter inherits Filter {
    filter(o : Object) : Bool {
        case o of
            p : Rank => true;
            e : Private => true;
            s : Corporal => true;
            c : Sergent => true;
            o : Officer => true;
            obj : Object => false;
        esac

    };
};

class SamePriceFilter inherits Filter {
    filter(o : Object) : Bool {
        let p : Product <- new Product in
            case o of
                s : Soda => s.getprice() = p.init(s.getName(), s.getModel(), s.getPrice()).getprice();
                c : Coffee => c.getprice() = p.init(c.getName(), c.getModel(), c.getPrice()).getprice();
                l : Laptop => l.getprice() = p.init(l.getName(), l.getModel(), l.getPrice()).getprice();
                r : Router => r.getprice() = p.init(r.getName(), r.getModel(), r.getPrice()).getprice();
                obj : Object => false;
            esac
    };
};


(****************************
 ** Classes Comparator-related **
 ****************************)
class PriceComparator inherits Comparator {
    compareTo(o1 : Object, o2 : Object) : Int {
        case o1 of
            p1 : Product => case o2 of
                                p2 : Product => p1.getprice() - p2.getprice();
                                o : Object => { abort(); 0; };
                            esac;
            o : Object => { abort(); 0; };
        esac
    };
};

-- I wanted to add a specific function for each rank class that compares the current rank with
-- other ranks because it s more modular that way, instead of casting the objects to each rank
-- and compare them here in RankComparator class
-- That way, when I want to add a new rank in the hierarchy, I dont need to modify rank comparator class,
-- and I only modify the Rank class and it s subclasses
class RankComparator inherits Comparator {
    compareTo(o1 : Object, o2 : Object) : Int {
        case o1 of
            r1 : Rank => case o2 of
                            r2 : Rank => r1.compareRankWith(r2);
                            o : Object => { abort(); 0; };
                         esac;
            o : Object => { abort(); 0; };
        esac
    };
};

class AlphabeticComparator inherits Comparator {
    compareTo(o1 : Object, o2 : Object) : Int {
        case o1 of
            s1 : String => case o2 of
                            s2 : String => if s1 < s2 then ~1 else if s1 = s2 then 0 else 1 fi fi;
                            o : Object => { abort(); 0; };
                           esac;
            o : Object => { abort(); 0; };
        esac
    };
    
};


(*******************************
 *** Array To Integer Class ***
 ***** imprumutata din lab *****
 *******************************)
class A2I {
    c2i(char : String) : Int {
        if char = "0" then 0 else
        if char = "1" then 1 else
        if char = "2" then 2 else
        if char = "3" then 3 else
        if char = "4" then 4 else
        if char = "5" then 5 else
        if char = "6" then 6 else
        if char = "7" then 7 else
        if char = "8" then 8 else
        if char = "9" then 9 else
        { abort(); 0; }  -- the 0 is needed to satisfy the typchecker
        fi fi fi fi fi fi fi fi fi fi
     };

    i2c(i : Int) : String {
        if i = 0 then "0" else
        if i = 1 then "1" else
        if i = 2 then "2" else
        if i = 3 then "3" else
        if i = 4 then "4" else
        if i = 5 then "5" else
        if i = 6 then "6" else
        if i = 7 then "7" else
        if i = 8 then "8" else
        if i = 9 then "9" else
        { abort(); ""; }  -- the "" is needed to satisfy the typchecker
        fi fi fi fi fi fi fi fi fi fi
    };

    a2i(s : String) : Int {
        if s.length() = 0 then 0 else
	    if s.substr(0,1) = "-" then ~a2i_aux(s.substr(1,s.length()-1)) else
        if s.substr(0,1) = "+" then a2i_aux(s.substr(1,s.length()-1)) else
        a2i_aux(s)
        fi fi fi
    };

    a2i_aux(s : String) : Int {
        (let int : Int <- 0 in {	
            (let j : Int <- s.length() in
                (let i : Int <- 0 in
                    while i < j loop {
                        int <- int * 10 + c2i(s.substr(i,1));
                        i <- i + 1;
                    } pool
                )
            );
            int;
        })
    };

    i2a(i : Int) : String {
        if i = 0 then "0" else 
        if 0 < i then i2a_aux(i) else
        "-".concat(i2a_aux(i * ~1)) 
        fi fi
    };
		
    i2a_aux(i : Int) : String {
        if i = 0 then "" else 
            (let next : Int <- i / 10 in
                i2a_aux(next).concat(i2c(i - next * 10))
            )
        fi
    };
};


(*******************************
 *** String Tokenizer Class ***
 *******************************)
class MyStringTokenizer {
    (*
        this method splits a string given as parameter into tokens (words, separated by spaces)
        and returns a list of the said tokens
    *)
    tokenize(s : String) : EmptyList {
        let tokens : EmptyList <- new EmptyList,
            t : String <- "" in {

            while not (s.length() = 0) loop
                let c : String <- s.substr(0, 1) in {

                    if c = " " then {
                        if not (t.length() = 0) then {
                            -- save the token built in 't', before reading space
                            tokens <- tokens.add(t);
                            t <- "";

                        } else {
                            -- reaches only when it reads a double space (shouldnt reach)
                            t <- "";
                        } fi;

                    } else {
                        -- add current character 'c' to token 't'
                        t <- t.concat(c);
                    } fi;

                    s <- s.substr(1, s.length() - 1);
                }
            pool;

            if not (t.length() = 0) then {  -- gets the last token (without space at the end)
                tokens <- tokens.add(t);
                t <- "";
            } else  -- reaches only when there is a space after the last token (shouldnt reach)
                t <- ""
            fi;
            
            tokens;
        }
    };

    (*
        this method gets a list of tokens (list of strings) given as parameter
        and forms a new object of a specific type, based on the info from the tokens
    *)
    createObjectFromTokens(tokens : EmptyList) : Object {
        let type_name : String <- tokens.getStringFromList(0),
            a2i : A2I <- new A2I in {
            if type_name = "Soda" then
                let name : String <- tokens.getStringFromList(1),
                    model : String <-tokens.getStringFromList(2),
                    price : String <- tokens.getStringFromList(3) in
                    let soda : Product <- new Soda.init(name, model, a2i.a2i(price)) in
                        soda

            else if type_name = "Coffee" then
                let name : String <- tokens.getStringFromList(1),
                    model : String <-tokens.getStringFromList(2),
                    price : String <- tokens.getStringFromList(3) in
                    let coffee : Product <- new Coffee.init(name, model, a2i.a2i(price)) in
                        coffee

            else if type_name = "Laptop" then
                let name : String <- tokens.getStringFromList(1),
                    model : String <-tokens.getStringFromList(2),
                    price : String <- tokens.getStringFromList(3) in
                    let laptop : Product <- new Laptop.init(name, model, a2i.a2i(price)) in
                        laptop

            else if type_name = "Router" then
                let name : String <- tokens.getStringFromList(1),
                    model : String <-tokens.getStringFromList(2),
                    price : String <- tokens.getStringFromList(3) in
                    let router : Product <- new Router.init(name, model, a2i.a2i(price)) in
                        router

            else if type_name = "Officer" then
                let name : String <- tokens.getStringFromList(1) in
                    let officer : Rank <- new Officer.init(name) in
                        officer

            else if type_name = "Sergent" then
                let name : String <- tokens.getStringFromList(1) in
                    let sergent : Rank <- new Sergent.init(name) in
                        sergent

            else if type_name = "Corporal" then
                let name : String <- tokens.getStringFromList(1) in
                    let corporal : Rank <- new Corporal.init(name) in
                        corporal

            else if type_name = "Private" then
                let name : String <- tokens.getStringFromList(1) in
                    let private : Rank <- new Private.init(name) in
                        private

            else if type_name = "Int" then
                let value : String <- tokens.getStringFromList(1) in
                    let int : Int <- a2i.a2i(value) in
                        int

            else if type_name = "String" then
                let value : String <- tokens.getStringFromList(1) in
                    let string : String <- value in
                        string
            
            else if type_name = "Bool" then
                let value : String <- tokens.getStringFromList(1) in
                    if value = "true" then
                        let bool : Bool <- true in
                            bool
                    else
                        let bool : Bool <- false in
                            bool
                    fi

            else if type_name = "IO" then
                let io : IO <- new IO in
                    io
            
            else
                abort()

            fi fi fi fi fi fi fi fi fi fi fi fi;
        }
    };
};



