package parse.errhandler;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

public class Cell {
    private Class<? extends TreeWalker> walker;
    private LinkedList<Class<? extends TreeWalker>> preprocesses;
    private LinkedList<ErrorType> errorTypes;

    {
	preprocesses = new LinkedList<Class<? extends TreeWalker>>();
	errorTypes = new LinkedList<ErrorType>();
    }

    public Cell() {
	this.walker = null;
    }

    public Cell(Class<? extends TreeWalker> walker) {
	this.walker = walker;
    }

    public Class<? extends TreeWalker> getWalker() {
	return walker;
    }

    public void setWalker(Class<? extends TreeWalker> walker) {
	this.walker = walker;
    }

    public List<Class<? extends TreeWalker>> getPreprocesses() {
	return preprocesses;
    }

    public List<ErrorType> getErrors() {
	return errorTypes;
    }

    public void setPreprocesses(LinkedList<Class<? extends TreeWalker>> preprocesses) {
	this.preprocesses = preprocesses;
    }

    public void setErrors(LinkedList<ErrorType> errorTypes) {
	this.errorTypes = errorTypes;
    }

    public Cell addPre(Class<? extends TreeWalker> classe) {
	preprocesses.add(classe);
	return this;
    }

    public Cell addErr(ErrorType errorType) {
	errorTypes.add(errorType);
	return this;
    }
}
