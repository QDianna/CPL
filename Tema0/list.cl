(****************************
 *** Classes List-related ***
 ****************************)
class EmptyList inherits IO {

    isEmpty() : Bool { true };

    getHead() : Object { { abort(); self; } };

    getTail() : EmptyList { { abort(); self; } };

    add(o : Object) : List { new List.init(o, self) };  -- cons

    size() : Int { 0 };

    deleteAt(index : Int) : EmptyList { { abort(); self; } };

    deleteAux(index : Int, currentIndex : Int) : EmptyList { { abort(); self; } };

    insertAt(index : Int, new_list : EmptyList) : EmptyList { { abort(); self; } };

    insertAtAux(index : Int, new_list : EmptyList) : EmptyList { { abort(); self; } };
    
    replace(index : Int, new_list : EmptyList) : EmptyList { {abort(); self; } };

    getListObjectFromList(index : Int) : EmptyList { { abort(); self; } };

    getStringFromList(index : Int) : String { { abort(); ""; } };

    getAux(real_index : Int) : Object { { abort(); self; } };

    toString() : String { "[  ]" };

    toStringList() : String { "" };

    reverse() : EmptyList { self };

    merge(other : EmptyList) : EmptyList { other };

    filterBy(f : Filter) : EmptyList { self };

    sortBy(c : Comparator) : EmptyList { self };

    insertSorted(element : Object, list : EmptyList, comparator : Comparator) : EmptyList { self.add(element) };
};


class List inherits EmptyList {

    head : Object;
    tail : EmptyList;

    init(h : Object, t : EmptyList) : List {
        {
            head <- h;
            tail <- t;
            self;
        }
    };

    isEmpty() : Bool { false };

    getHead() : Object { head };

    getTail() : EmptyList { tail };

    size() : Int { 1 + tail.size() };

    (*
        this method calls a recursive method that "deletes" an object from a specified position in the current (self) list

        the real index is calculated because, due to the logic of constructing
        (which is always adding at the begining of the list), lists are always reversed
        
        so, for example, when index = 1 that actually means the last object from the list
    *)
    deleteAt(index : Int) : EmptyList {
        let real_index : Int <- size() - index - 1,
            new_list : EmptyList <- new EmptyList in
                new_list <- deleteAux(real_index, 0)
    };

    (*
        this method created a new list from the current (self) list, "deleting"/not including an element from a specific position
    *)
    deleteAux(index : Int, currentIndex : Int) : EmptyList {
        if currentIndex = index then
            -- skip (delete) head, return tail
            tail
        else
            -- keep current head, recursive call
            new List.init(head, tail.deleteAux(index, currentIndex + 1))
        fi
    };

    (*
        this method calls a recursive method that "inserts" an object from a specified position in the current (self) list
    *)
    insertAt(index : Int, new_list_object : EmptyList) : EmptyList {
        let real_index : Int <- size() - index - 1,
            new_list : EmptyList <- new EmptyList in
                new_list <- insertAtAux(real_index, new_list_object)
    };

    (*
        this method created a new list from the current (self) list, adding a new element at a specific position
    *)
    insertAtAux(index : Int, new_list_object : EmptyList) : EmptyList {
        if index = 0 then
            -- make the element the head of the new list
            new List.init(new_list_object, self)
        else
            -- keep current head, recusrive call
            new List.init(head, tail.insertAt(index - 1, new_list_object))
        fi
    };

    (*
        this method created a new list from the current (self) list, replacing a specific element
        
        replacing means inserting the new object at the position specified and deleting the old one
    *)
    replace(index : Int, new_list_object : EmptyList) : EmptyList {
       let new_list : EmptyList <- self.insertAt(index, new_list_object) in {
            new_list <- new_list.deleteAt(index);
            new_list;
       }
    };


    (*
        this method extracts an object from the current/self list, checks if it's a list and returns it if so
        if it s not a list, the method abords

        this method is used for the merge command, to extract the 2 lists that need to be merged
    *)
    getListObjectFromList(index : Int) : EmptyList {
        let real_index : Int <- size() - index - 1 in
            let o : Object <- getAux(real_index),
            a2i : A2I <- new A2I in
                case o of
                    l : List => l;
                    obj : Object => { abort(); self; };
                esac
    };

    (*
        this method extracts an object from the current/self list, checks if it's a list or a string
        and returns the 'toString()' result on that object

        this method is used for processing comands - string
        and for the print command - lists
    *)
    getStringFromList(index : Int) : String {
        let real_index : Int <- size() - index - 1 in
            let o : Object <- getAux(real_index),
            a2i : A2I <- new A2I in
                case o of
                    s : String => s;  -- a command
                    el : EmptyList => el.toString();  -- a list
                    o : Object => { abort(); ""; };
                esac
    };

    getAux(real_index : Int) : Object { if real_index = 0 then head else tail.getAux(real_index - 1) fi };

    toString() : String { "[ ".concat(reverse().toStringList()).concat(" ]") };

    toStringList() : String {
        let current_object : Object <- head,
            a2i : A2I <- new A2I
        in 
            let result_string : String <- 
                (case current_object of
                    i : Int => "Int(".concat(a2i.i2a(i)).concat(")");
                    s : String => "String(".concat(s).concat(")");
                    b : Bool => if b then "Bool(true)" else "Bool(false)" fi;
                    p : Product => p.toString();
                    r : Rank => r.toString();
                    l : List => l.toString();
                    i : IO => "IO()";
                esac)
            in
                (case tail of
                    el : EmptyList => result_string;
                    l : List => result_string.concat(", ").concat(tail.toStringList());
                esac)
    };

    reverse() : EmptyList { tail.reverse().merge(new EmptyList.add(head)) };

    merge(other : EmptyList) : EmptyList { tail.merge(other).add(head) };

    filterBy(f : Filter) : EmptyList {
        if f.filter(head) then
            -- add current head to new list if it passes the filter
            new List.init(head, tail.filterBy(f))
        else
            -- skip it if not
            tail.filterBy(f)
        fi
    };

    (*
        this method uses the 'insertSorted' recursive method to sort the current/self list
        by extracting each element and inserting it in a new list at the right spot
    *)
    sortBy(comparator : Comparator) : EmptyList {
        let sorted_tail : EmptyList <- tail.sortBy(comparator) in
            insertSorted(head, sorted_tail, comparator)
    };

    (*
        this method finds the right spot for 'element' in 'list'
    *)
    insertSorted(element : Object, list : EmptyList, comparator : Comparator) : EmptyList {
        if list.isEmpty() then
            new List.init(element, list)
        else
            let comparison : Int <- comparator.compareTo(element, list.getHead()) in
                if comparison <= 0 then
                     -- head is smaller than element, so head stays and element is inserted somewhere in the sorted tail
                    new List.init(list.getHead(), insertSorted(element, list.getTail(), comparator))
                else
                    -- head is bigger than element, so element is new head
                    new List.init(element, list)
                fi
        fi
    };
};



