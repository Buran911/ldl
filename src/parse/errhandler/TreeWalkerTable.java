package parse.errhandler;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

public class TreeWalkerTable {
    private LinkedList<Cell> treeTable = new LinkedList<Cell>();

    public boolean add(TreeWalker walker, LinkedList<TreeWalker> preprocess, LinkedList<ErrorType> error) {
	for (Cell cell : treeTable) {
	    if (cell.getWalker() == walker) {
		return false;
	    }
	}
	return treeTable.add(new Cell(walker, preprocess, error));
    }

    public List<TreeWalker> getProcessesByWalker(TreeWalker walker) {
	for (Cell cell : treeTable) {
	    if (cell.getWalker() == walker) {
		return cell.getPreprocesses();
	    }
	}
	return null;
    }

    public List<ErrorType> getErrorsByWalker(TreeWalker walker) {
	for (Cell cell : treeTable) {
	    if (cell.getWalker() == walker) {
		return cell.getErrors();
	    }
	}
	return null;
    }
    
    public static void main(String args[]){
	TreeWalkerTable twt = new TreeWalkerTable();
	LinkedList<TreeWalker> one = new LinkedList<TreeWalker>();
	one.add(null);
	LinkedList<ErrorType> two = new LinkedList<ErrorType>();
	two.add(ErrorType.OperandNotFound);
	
	twt.add(null, one , two);
	
	System.out.print("end");
    }
}

class Cell {
    private TreeWalker walker;
    private LinkedList<TreeWalker> preprocesses;
    private LinkedList<ErrorType> errorTypes;
    private boolean executed; 

    {
	executed = false;
    }
    
    Cell(TreeWalker walker, LinkedList<TreeWalker> preprocess, LinkedList<ErrorType> errorTypes) {
	this.walker = walker;
	this.preprocesses = preprocess;
	this.errorTypes = errorTypes;
    }

    public TreeWalker getWalker() {
	return walker;
    }

    public void setWalker(TreeWalker walker) {
	this.walker = walker;
    }

    public List<TreeWalker> getPreprocesses() {
        return preprocesses;
    }

    public List<ErrorType> getErrors() {
        return errorTypes;
    }

    public void setPreprocesses(LinkedList<TreeWalker> preprocesses) {
        this.preprocesses = preprocesses;
    }

    public void setErrors(LinkedList<ErrorType> errorTypes) {
        this.errorTypes = errorTypes;
    }
}