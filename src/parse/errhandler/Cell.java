package parse.errhandler;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

public class Cell {
    private Class<? extends TreeWalker> walker;
    private LinkedList<Class<? extends TreeWalker>> preprocesses;
    private LinkedList<ErrorType> errorTypes;

    public Cell(Class<? extends TreeWalker> walker, LinkedList<Class<? extends TreeWalker>> preprocess, LinkedList<ErrorType> errorTypes) {
	this.walker = walker;
	this.preprocesses = preprocess;
	this.errorTypes = errorTypes;
    }

    public Cell() {
	walker = null;
	preprocesses = null;
	errorTypes = null;
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
}
