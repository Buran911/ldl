package parse.errhandler;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class Cell {
    private TreeWalker treeWalker;
    private Class<? extends TreeWalker> walkerClass;
    private LinkedList<Cell> precellList;
    private LinkedList<Cell> postcellList;
    private LinkedList<ErrorType> errorTypes;
    private boolean available = true;
    private boolean checker = false;
    private Logger logger = Logger.getLogger(Cell.class);

    {
	precellList = new LinkedList<Cell>();
	postcellList = new LinkedList<Cell>();
	errorTypes = new LinkedList<ErrorType>();
    }

    public Cell() {
	this.walkerClass = null;
    }

    public Cell(Class<? extends TreeWalker> walker) {
	this.walkerClass = walker;
    }

    public Class<? extends TreeWalker> getWalkerClass() {
	return walkerClass;
    }

    public List<ErrorType> getErrors() {
	return errorTypes;
    }

    public Cell addPre(Cell cell) {
	precellList.add(cell);
	return this;
    }

    public Cell addPost(Cell cell) {
	postcellList.add(cell);
	return this;
    }

    public Cell remPost(Cell cell) {
	postcellList.remove(cell);
	return this;
    }

    public Cell remPre(Cell cell) {
	precellList.remove(cell);
	return this;
    }

    public Cell addErr(ErrorType errorType) {
	errorTypes.add(errorType);
	return this;
    }

    public LinkedList<Cell> getPrecellList() {
	return precellList;
    }

    public LinkedList<Cell> getPostcellList() {
	return postcellList;
    }

    public boolean isChecker() {
	return checker;
    }

    public Cell setChecker(boolean checker) {
	this.checker = checker;
	return this;
    }

    public boolean isAvailable() {
	return available;
    }

    public void setAvailable(boolean available) {
	this.available = available;
    }

    public TreeWalker getTreeWalker() {
	return treeWalker;
    }

    public void setTreeWalker(TreeWalker treeWalker) {
	this.treeWalker = treeWalker;
    }

    public void disappearDR() {
	for (Cell cell : precellList) {
	    cell.remPost(this);
	}
	for (Cell cell : postcellList) {
	    cell.remPre(this);
	}
	treeWalker = null;
	walkerClass = null;
	precellList = null;
	postcellList = null;
	errorTypes = null;
	available = false;
    }

    public void disappear() {
	if (precellList.size() == 0) {
	    for (Cell postcell : postcellList) {
		postcell.remPre(this);
	    }
	}
	else if (postcellList.size() == 0) {
	    for (Cell precell : precellList) {
		precell.remPost(this);
	    }
	}
	else {
	    for (Cell precell : precellList) {
		for (Cell postcell : postcellList) {
		    // Если есть уже ссылка , то добавлять не надо
		    if (!precell.postcellList.contains(postcell))
			precell.addPost(postcell);
		    if (!postcell.precellList.contains(precell))
			postcell.addPre(precell);
		    postcell.remPre(this);
		}
		precell.remPost(this);
	    }
	}

	treeWalker = null;
	walkerClass = null;
	precellList = null;
	postcellList = null;
	errorTypes = null;
	available = false;
    }

    public void delSubTree(Cell cellParam) {
	if (cellParam.postcellList != null)
	    for (Cell cell : cellParam.postcellList) {
		delSubTree(cell);

	    }
	cellParam.disappearDR();
    }

    public void delSubTree() {
	if (postcellList != null)
	    // Тут ошибка - for удаляет только один элемент из 2-х и
	    // заканчивается
	    while (postcellList.size() != 0)
		for (Cell cell : postcellList) {
		    logger.debug(" --- cell - " + cell.getWalkerClass().getSimpleName());
		    cell.setAvailable(false);
		    delSubTree(cell);
		}
	disappearDR();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((walkerClass == null) ? 0 : walkerClass.hashCode());
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
	if (walkerClass == null) {
	    if (other.walkerClass != null)
		return false;
	}
	else if (!walkerClass.equals(other.walkerClass))
	    return false;
	return true;
    }

    public void printPersUndPos() {
	if (walkerClass != null) {
	    logger.debug(" -- " + walkerClass.getSimpleName());
	    for (Cell precell : precellList)
		logger.debug(" ----- pre - " + precell.getWalkerClass().getSimpleName());
	    for (Cell postcell : postcellList)
		logger.debug(" ----- post - " + postcell.getWalkerClass().getSimpleName());
	}
    }
}