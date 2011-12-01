package parse.errhandler;

import java.util.LinkedList;


public interface Checker {
    
    public LinkedList<ErrorType> getErrorTypes();
    
}
