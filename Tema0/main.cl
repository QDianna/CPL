(*******************************
 ********* Main Class *********
 *******************************)
class Main inherits IO {
    t : MyStringTokenizer <- new MyStringTokenizer;
    a2i : A2I <- new A2I;
    
    read_looping : Bool <- true;
    interact_looping : Bool <- false;

    list_of_lists : EmptyList <- new EmptyList;
    
    (* 
        this method reads data from an input file, creates objects from each line, creates a list
        with all the objects and, finally, adds the resulted list in 'list_of_lists'
    *)
    readFromFile() : Object {
        let obj_list : EmptyList <- new EmptyList,
            tokenizer : MyStringTokenizer <- new MyStringTokenizer in {

            while read_looping loop {
                -- read each line and form a list of tokens
                -- tokens are strings that represent each word from a line
                let line : String <- in_string() in {
                
                    if line = "END" then {
                        read_looping <- false;

                        if not obj_list.isEmpty() then {
                            -- the list of objects from the file gets added to 'list_of_lists'
                            list_of_lists <- list_of_lists.add(obj_list);

                        } else {
                            out_string("Error: no data found to create list.\n");  -- debugg
                        } fi;

                    } else
                        -- 'tokenize' returns a list of tokens (words)
                        -- 'createObjectFromTokens' gets a list of tokens and returns an object
                        -- the object gets added to 'obj_list'
                        let obj : Object <- tokenizer.createObjectFromTokens(tokenizer.tokenize(line)) in
                                obj_list <- obj_list.add(obj)
                    fi;
                };
            } pool;
        }
    };

    (* 
        this method prints
            a. a specific list from 'list_of_lists' when the parameter 'i' is a valid index
            b. the whole 'list_of_lists' when the parameter 'i' is -1
    *)
    printListOfLists(i : Int) : Object {
        if i = ~1 then
            let current_index : Int <- 1 in {

                while current_index <= list_of_lists.size() loop {
                    -- method 'get' used here is actually a 'toString()' call on the object of index 'i'
                    -- in this case is a List.toString()
                    let current_list : String <- list_of_lists.getStringFromList(current_index - 1) in {
                        
                        out_string(a2i.i2a(current_index).concat(": ").concat(current_list).concat("\n"));
                        current_index <- current_index + 1;
                    };
                } pool;
            }

        else
            out_string(list_of_lists.getStringFromList(i).concat("\n"))
        fi
    };

    (*
        this method is used to check index validity in a list
    *)
    isValidIndex(index : Int, list : EmptyList) : Bool {
        if index < 0 then
            false
        else if not index < list.size() then
            false
        else
            true
        fi fi
    };

    (*
        the main method implements the logic of the main program
        first it reads data from STDIN and populates a list with objects created from said data
        then it enters an interactive prompt, awaiting commands from STDIN and it performs said commands
    *)
    main() : Object {

        let o : Object <- readFromFile() in {

            interact_looping <- true;

            while interact_looping loop {
                let command : EmptyList <- t.tokenize(in_string()) in {
                        
                    if command.getStringFromList(0) = "load" then {
                        read_looping <- true;
                        readFromFile();
                    }

                    else if command.getStringFromList(0) = "print" then {
                        -- print command can be followed, or not, by an index for printing a specific list from 'list_of_lists'
                        if command.size() = 1 then {
                            printListOfLists(~1);  -- '-1' given as parameter means print all lists

                        } else {
                            let index : Int <- a2i.a2i(command.getStringFromList(1)) - 1 in
                                if not isValidIndex(index, list_of_lists) then {
                                    out_string("Error: invalid index. Please use a number from 1 to ".concat(a2i.i2a(list_of_lists.size())).concat(".\n"));
                                    abort();
                                
                                } else
                                    printListOfLists(index)  -- valid index given as parameter means print specific list
                                fi;
                        } fi;
                    }

                    else if command.getStringFromList(0) = "merge" then {
                        -- merge command needs to be followed by 2 indexes
                        if (not (command.size() = 3)) then {
                            out_string("Usage: merge index1 index2.\n");
                            abort();

                        } else {
                            let merged_list : EmptyList,
                                index1 : Int <- a2i.a2i(command.getStringFromList(1)) - 1,
                                index2 : Int <- a2i.a2i(command.getStringFromList(2)) - 1 in {
                                    if not isValidIndex(index1, list_of_lists) then {
                                        out_string("Error: invalid index. Please use a number from 1 to ".concat(a2i.i2a(list_of_lists.size())).concat(".\n"));
                                        abort();
                                    }

                                    else if not isValidIndex(index2, list_of_lists) then {
                                        out_string("Error: invalid index. Please use a number from 1 to ".concat(a2i.i2a(list_of_lists.size())).concat(".\n"));
                                        abort();
                                    }

                                    else {
                                        merged_list <- list_of_lists.getListObjectFromList(index2).merge(list_of_lists.getListObjectFromList(index1));
                                        -- delete in descending order of indexes so that it doesnt mess up the list
                                        if index1 < index2 then 
                                            list_of_lists <- list_of_lists.deleteAt(index2).deleteAt(index1)
                                        else 
                                            list_of_lists <- list_of_lists.deleteAt(index1).deleteAt(index2)
                                        fi;
                                        -- add merged list to the end of the list of lists
                                        list_of_lists <- list_of_lists.add(merged_list);
                                    } fi fi;
                            };
                        } fi;
                    }

                    else if command.getStringFromList(0) = "filterBy" then {
                        let index : Int <- a2i.a2i(command.getStringFromList(1)) - 1,
                            filter_mode : String <- command.getStringFromList(2),
                            filtered_list : EmptyList <- new EmptyList,
                            f : Filter in {

                                if not isValidIndex(index, list_of_lists) then {
                                    out_string("Error: invalid index. Please use a number from 1 to ".concat(a2i.i2a(list_of_lists.size())).concat(".\n"));
                                    abort();
                                }

                                else if filter_mode = "ProductFilter" then {
                                    f <- new ProductFilter;
                                } 
                                
                                else if filter_mode = "RankFilter" then {
                                    f <- new RankFilter;
                                }

                                else if filter_mode = "SamePriceFilter" then {
                                    f <- new SamePriceFilter;
                                }

                                else {
                                    out_string("Error: unknown filter method. Use one of the following: {ProductFilter,RankFilter,SamePriceFilter}\n");
                                    abort();
                                } fi fi fi fi;

                                -- method getListObjectFromList used here works like the 'getStringFromList' method but returns the object - MUST BE LIST
                                filtered_list <- list_of_lists.getListObjectFromList(index).filterBy(f);
                                
                                -- add the newly filtered list to the list of lists and remove the old one
                                -- replace inserts the new list at the position 'index' and deletes the list that was previously there
                                list_of_lists <- list_of_lists.replace(index, filtered_list);
                            };
                    }

                    else if command.getStringFromList(0) = "sortBy" then {
                        let index : Int <- a2i.a2i(command.getStringFromList(1)) - 1,
                            sort_mode : String <- command.getStringFromList(2),
                            sort_order : String <- command.getStringFromList(3),
                            sorted_list : EmptyList <- new EmptyList,
                            c : Comparator in {

                                if not isValidIndex(index, list_of_lists) then {
                                    out_string("Error: invalid index. Please use a number from 1 to ".concat(a2i.i2a(list_of_lists.size())).concat(".\n"));
                                    abort();
            
                                } else {
                                    if sort_mode = "PriceComparator" then {
                                        c <- new PriceComparator;
                                    } 
                                    
                                    else if sort_mode = "RankComparator" then {
                                        c <- new RankComparator;
                                    }

                                    else if sort_mode = "AlphabeticComparator" then {
                                        c <- new AlphabeticComparator;
                                    }

                                    else {
                                        out_string("Error: unknown comparator method. Use one of the following: {PriceComparator,RankComparator,AlphabeticComparator}\n");
                                        abort();
                                    } fi fi fi;

                                    if sort_order = "ascendent" then {
                                        sorted_list <- list_of_lists.getListObjectFromList(index).sortBy(c);
                                    }
                                    else if sort_order = "descendent" then {
                                        sorted_list <- list_of_lists.getListObjectFromList(index).sortBy(c).reverse();
                                    }
                                    else {
                                        out_string("Error: unknown sorting mode. Use one of the following: {ascendent, descendent}\n");
                                        abort();
                                    } fi fi;
                                    
                                    list_of_lists <- list_of_lists.replace(index, sorted_list);
                                } fi;
                            };
                    }

                    else if command.getStringFromList(0) = "help" then {
                        out_string("Commands: help, load, print, merge, filterBy, sortBy.\n");
                    }

                    else if command.getStringFromList(0) = "exit" then {
                        out_string("Exiting interactive prompt...\n");
                        interact_looping <- false;
                    }

                    else {
                        out_string("Error: unknown command. Use 'help' to see all commands.\n");
                        abort();
                    } fi fi fi fi fi fi fi;
                };
            } pool;
        }
    };
};



