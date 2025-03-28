-- (*******************************
--  *** Classes Product-related ***
--  *******************************)
class Product inherits IO {
    name : String;
    model : String;
    price : Int;

    init(n : String, m: String, p : Int) : SELF_TYPE { {
        name <- n;
        model <- m;
        price <- p;
        self;
    }};

    getName() : String { name };

    getModel() : String { model };

    getPrice() : Int { price };

    -- computes price 
    getprice() : Int { price * 119 / 100 };

    toString():String {
        type_name().concat("(").concat(name).concat(";").concat(model).concat(")")
    };
};

class Edible inherits Product {
    -- VAT tax is lower for foods
    getprice():Int { price * 109 / 100 };
};

class Soda inherits Edible {
    -- sugar tax is 20 bani
    getprice():Int { price * 109 / 100 + 20 };
};

class Coffee inherits Edible {
    -- this is technically poison for ants
    getprice():Int { price * 119 / 100 };
};

class Laptop inherits Product {
    -- operating system cost included
    getprice():Int { price * 119 / 100 + 499 };
};

class Router inherits Product {};


-- (****************************
--  *** Classes Rank-related ***
--  ****************************)
class Rank inherits IO {
    name : String;

    init(n : String) : SELF_TYPE {{
        name <- n;
        self;
    }};

    toString() : String {
        -- Hint: what are the default methods of Object?
        type_name().concat("(").concat(name).concat(")")
    };

    compareRankWith(other_rank : Rank) : Int  { {out_string("ajunge aici?\n"); 0; } };
};

class Private inherits Rank {
    compareRankWith(other_rank : Rank) : Int  {
        case other_rank of
            p : Private => 0;
            c : Corporal => ~1;
            s : Sergent => ~1;
            o : Officer => ~1;
            obj : Object => { abort(); ~2; };
        esac
    };
};

class Corporal inherits Private {
    compareRankWith(other_rank : Rank) : Int  {
        case other_rank of
            p : Private => 1;
            c : Corporal => 0;
            s : Sergent => ~1;
            o : Officer => ~1;
            obj : Object => { abort(); ~2; };
        esac
    };
};

class Sergent inherits Corporal {
        compareRankWith(other_rank : Rank) : Int  {
        case other_rank of
            p : Private => 1;
            c : Corporal => 1;
            s : Sergent => 0;
            o : Officer => ~1;
            obj : Object => { abort(); ~2; };
        esac
    };
};

class Officer inherits Sergent {
        compareRankWith(other_rank : Rank) : Int  {
        case other_rank of
            p : Private => 1;
            c : Corporal => 1;
            s : Sergent => 1;
            o : Officer => 0;
            obj : Object => { abort(); ~2; };
        esac
    };
};



