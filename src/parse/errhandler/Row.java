package parse.errhandler;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

public class Row {
    private List<Class<? extends TreeWalker>> dependList;
    private List<ErrorType> errors;
    private boolean checker = false;
    {
	dependList = new LinkedList<Class<? extends TreeWalker>>();
	errors = new LinkedList<ErrorType>();
    }
    public List<Class<? extends TreeWalker>> getDependList() {
        return dependList;
    }
    public Row addPre(Class<? extends TreeWalker> classe) {
        dependList.add(classe);
        return this;
    }
    public List<ErrorType> getErrorList() {
        return errors;
    }
    public Row addErr(ErrorType errorType) {
	errors.add(errorType);
	return this;
    }
    public boolean isChecker() {
        return checker;
    }
    public Row Checker() {
        this.checker = true;
        return this;
    }
}