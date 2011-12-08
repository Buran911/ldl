package parse.errhandler;

import generation.walkers.TreeWalker;
import generation.walkers.strategys.BottomUpWalkingStrategy;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.strategys.TopDownWalkingStrategy;
import generation.walkers.walkers.FunctionalImplementedChecker;
import generation.walkers.walkers.IdConvertor;
import generation.walkers.walkers.IdNotDefinedChecker;
import generation.walkers.walkers.IdRedefinedChecker;
import generation.walkers.walkers.IdTableFiller;
import generation.walkers.walkers.IdTableMaker;
import generation.walkers.walkers.PositionEstimater;
import generation.walkers.walkers.TypeMismatchChecker;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeWalkerTable {
    private static LinkedList<Cell> treeTable;
    private LinkedList<Class> unreachableWalkers;
    private LinkedList<Cell> table;

    {
	treeTable = new LinkedList<Cell>();
	unreachableWalkers = new LinkedList<Class>();

	initTable();
    }

    private TreeWalkerTable() {

    }

    private void initTable() {
	treeTable.add(new Cell(PositionEstimater.class, new LinkedList<Class>(), new LinkedList<ErrorType>()));

	LinkedList<Class> fic = new LinkedList<Class>();
	fic.add(PositionEstimater.class);
	treeTable.add(new Cell(FunctionalImplementedChecker.class, fic, new LinkedList<ErrorType>()));

	LinkedList<Class> tm = new LinkedList<Class>();
	tm.add(PositionEstimater.class);
	treeTable.add(new Cell(IdTableMaker.class, tm, new LinkedList<ErrorType>()));

	LinkedList<Class> irc = new LinkedList<Class>();
	irc.add(PositionEstimater.class);
	treeTable.add(new Cell(IdRedefinedChecker.class, irc, new LinkedList<ErrorType>()));

	LinkedList<Class> indc = new LinkedList<Class>();
	indc.add(IdTableMaker.class);
	treeTable.add(new Cell(IdRedefinedChecker.class, indc, new LinkedList<ErrorType>()));

	LinkedList<Class> itf = new LinkedList<Class>();
	itf.add(IdNotDefinedChecker.class);
	treeTable.add(new Cell(IdTableFiller.class, itf, new LinkedList<ErrorType>()));

	LinkedList<Class> id = new LinkedList<Class>();
	id.add(IdNotDefinedChecker.class);
	treeTable.add(new Cell(IdConvertor.class, id, new LinkedList<ErrorType>()));

	LinkedList<Class> tmc = new LinkedList<Class>();
	tmc.add(IdTableFiller.class);
	tmc.add(IdConvertor.class);
	LinkedList<ErrorType> tmce = new LinkedList<ErrorType>();
	tmce.add(ErrorType.IdentifierRedefenition);
	tmce.add(ErrorType.IdentifierUndefined);
	treeTable.add(new Cell(TypeMismatchChecker.class, tmc, tmce));
    }

    public static TreeWalkerTable getInstance(List<TreeWalker> walkers) {
	table = new TreeWalkerTable();
	table.treeTable.clear();

	deleteUnusedWalkers(walkers, table);
	deleteUnreachableWalkers(walkers, table);

	return table;
    }

    private static void deleteUnusedWalkers(List<TreeWalker> walkers, TreeWalkerTable table) {
	for (TreeWalker walker : walkers) {
	    Integer position = indexOf(walker.getClass());
	    table.add(treeTable.get(position), table);
	}
    }

    private void add(Cell cell, TreeWalkerTable table) {
	table.treeTable.add(cell);
    }

    private static void deleteUnreachableWalkers(List<TreeWalker> walkers, TreeWalkerTable table) {
	boolean searchFinished = false;
	Queue<Class> unreachableWalkersQueue = new LinkedList<Class>();
	unreachableWalkersQueue.addAll((Collection<? extends Class>) table.unreachableWalkers.clone());

	while (unreachableWalkersQueue.size() > 0) {
	    Class walker = unreachableWalkersQueue.poll();
	    LinkedList<Cell> removeList = new LinkedList<Cell>();
	    for (Cell cell : table.treeTable) {
		if (cell.getPreprocesses().contains(walker)) {
		    table.unreachableWalkers.add(walker);
		    unreachableWalkersQueue.offer(walker);
		    removeList.add(cell);
		}
	    }
	    for (Cell list : removeList) {
		table.treeTable.remove(list);
	    }
	}
    }

    private static int indexOf(Class cl) {
	for (int i = 0; i < treeTable.size(); i++) {
	    if (treeTable.get(i).getWalker() == cl) {
		return i;
	    }
	}

	return -1;
    }

    public static void main(String args[]) {
	LinkedList<TreeWalker> walkers = new LinkedList<TreeWalker>();
	walkers.add(new PositionEstimater(new IdParsigStrategy()));
	walkers.add(new FunctionalImplementedChecker(new BottomUpWalkingStrategy(), null));
	walkers.add(new TypeMismatchChecker(new IdParsigStrategy(), null));

	TreeWalkerTable table = TreeWalkerTable.getInstance(walkers);
	System.out.print("g");
    }
}

class Cell {
    private Class walker;
    private LinkedList<Class> preprocesses;
    private LinkedList<ErrorType> errorTypes;

    public Cell(Class walker, LinkedList<Class> preprocess, LinkedList<ErrorType> errorTypes) {
	this.walker = walker;
	this.preprocesses = preprocess;
	this.errorTypes = errorTypes;
    }

    public Class getWalker() {
	return walker;
    }

    public void setWalker(Class walker) {
	this.walker = walker;
    }

    public List<Class> getPreprocesses() {
	return preprocesses;
    }

    public List<ErrorType> getErrors() {
	return errorTypes;
    }

    public void setPreprocesses(LinkedList<Class> preprocesses) {
	this.preprocesses = preprocesses;
    }

    public void setErrors(LinkedList<ErrorType> errorTypes) {
	this.errorTypes = errorTypes;
    }
}