package parse.errhandler;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

public class Cell implements Cloneable {
    private Class<? extends TreeWalker> walker;
    private LinkedList<Class<? extends TreeWalker>> preproc;
    private LinkedList<Class<? extends TreeWalker>> postproc;
    private LinkedList<ErrorType> errorTypes;
    // Поле-флаг для отображения , выполнился ли данный обходчик
    private boolean passed = false;
    private boolean checker = false;

    {
	preproc = new LinkedList<Class<? extends TreeWalker>>();
	postproc = new LinkedList<Class<? extends TreeWalker>>();
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((walker == null) ? 0 : walker.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Cell other = (Cell) obj;
	if (walker == null) {
	    if (other.walker != null)
		return false;
	}
	else if (!walker.equals(other.walker))
	    return false;
	return true;
    }

    public List<ErrorType> getErrors() {
	return errorTypes;
    }

    public Cell addPre(Class<? extends TreeWalker> classe) {
	preproc.add(classe);
	return this;
    }

    public Cell addPost(Class<? extends TreeWalker> classe) {
	postproc.add(classe);
	return this;
    }

    public Cell remPost() {
	postproc = new LinkedList<Class<? extends TreeWalker>>();
	return this;
    }

    public Cell remPre() {
	preproc = new LinkedList<Class<? extends TreeWalker>>();
	return this;
    }

    public Cell addErr(ErrorType errorType) {
	errorTypes.add(errorType);
	return this;
    }

    public boolean isPassed() {
	return passed;
    }

    public Cell setPassed(boolean passed) {
	this.passed = passed;
	return this;
    }

    public LinkedList<Class<? extends TreeWalker>> getPreproc() {
	return preproc;
    }

    public LinkedList<Class<? extends TreeWalker>> getPostproc() {
	return postproc;
    }

    public boolean isChecker() {
	return checker;
    }

    public Cell setChecker(boolean checker) {
	this.checker = checker;
	return this;
    }

    public void setPostproc(LinkedList<Class<? extends TreeWalker>> postproc) {
	this.postproc = postproc;
    }

    public void setPreproc(LinkedList<Class<? extends TreeWalker>> preproc) {
	this.preproc = preproc;
    }

    @Override
    public Cell clone() {
	Cell cell = new Cell();

	cell.walker = walker;
	cell.preproc = new LinkedList<Class<? extends TreeWalker>>();
	for (Class<? extends TreeWalker> classe : preproc) {
	    cell.addPre(classe);
	}
	for (Class<? extends TreeWalker> classe : postproc) {
	    cell.addPost(classe);
	}

	for(ErrorType errorType : errorTypes){
	    cell.addErr(errorType);
	}
	cell.passed = passed;
	cell.checker = checker;

	return cell;
    }

}
