package parse.errhandler;

import generation.walkers.TreeWalker;
import generation.walkers.strategys.BottomUpWalkingStrategy;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.FunctionalImplementedChecker;
import generation.walkers.walkers.IdConvertor;
import generation.walkers.walkers.IdNotDefinedChecker;
import generation.walkers.walkers.IdRedefinedChecker;
import generation.walkers.walkers.IdTableFiller;
import generation.walkers.walkers.IdTableMaker;
import generation.walkers.walkers.PositionEstimater;
import generation.walkers.walkers.TypeMismatchChecker;

import java.util.LinkedList;
import java.util.List;

public class Start {
    public static void main(String args[]) {

	LinkedList<TreeWalker> walkers = new LinkedList<TreeWalker>();
	// walkers.add(new PositionEstimater(new IdParsigStrategy()));
	walkers.add(new FunctionalImplementedChecker(new BottomUpWalkingStrategy(), null));
	walkers.add(new IdRedefinedChecker(new IdParsigStrategy(), null));
	walkers.add(new IdTableMaker(new IdParsigStrategy(), null));
	walkers.add(new IdTableFiller(new IdParsigStrategy(), null));
	walkers.add(new IdNotDefinedChecker(new IdParsigStrategy(), null, null));
	walkers.add(new IdConvertor(new IdParsigStrategy(), null));
	walkers.add(new TypeMismatchChecker(new IdParsigStrategy(), null));
	// TreeWalkerTable
	TreeWalkerTable twt = new TreeWalkerTable();

	twt.deleteUnusedWalkers(walkers);

	twt.deleteUnreachableWalkers(walkers);
	// TreeWalkerTable table = TreeWalkerTable.getInstance(walkers);
	System.out.print("Ende");
    }
}

class TreeWalkerTable {
    private final static LinkedList<Cell> treeTable;// Таблица где все
						    // зависимости
						    // walker'ов (одна для всех)
						    // private LinkedList<Class>
						    // unreachableWalkers;
    LinkedList<Cell> table;
    LinkedList<Cell> unreachableWalkers;
    static {
	treeTable = new LinkedList<Cell>();

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
	treeTable.add(new Cell(IdNotDefinedChecker.class, indc, new LinkedList<ErrorType>()));

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

    {
	table = new LinkedList<Cell>();
	unreachableWalkers = new LinkedList<Cell>();
    }

    TreeWalkerTable() {
    }

    // public static TreeWalkerTable getInstance(List<TreeWalker> walkers) {
    // TreeWalkerTable table = new TreeWalkerTable();
    // table.treeTable.clear();
    //
    // deleteUnusedWalkers(walkers, table);
    // deleteUnreachableWalkers(walkers, table);
    //
    // return null;
    // }

    void deleteUnusedWalkers(List<TreeWalker> walkers) {
	for (TreeWalker walker : walkers) {
	    int position = indexOf(walker.getClass());
	    table.add(treeTable.get(position));
	}
    }

    List<Class> getProcessesByWalker(Class walker) {
	for (Cell cell : treeTable) {
	    if (cell.getWalker() == walker) {
		return cell.getPreprocesses();
	    }
	}
	return null;
    }

    /** Прямое добавление ячейки в таблицу */
    private void add(Cell cell, TreeWalkerTable table) {
	table.treeTable.add(cell);
    }

    boolean deleteUnreachableWalkers(List<TreeWalker> walkers) {
	boolean fg = false;
	for (Cell cell : table) {
	    boolean found_flag = false;
	    System.out.println("Cell : " + cell.getWalker().toString());
	    if (cell.getPreprocesses().size() == 0) {
		System.out.println("No preprocess");
		found_flag = true;
		fg = true;
	    }
	    else {
		for (Class ll : cell.getPreprocesses()) {
		    System.err.println("	ll : " + ll.toString());
		    for (TreeWalker walker : walkers) {
			System.out.println("		Walker : " + walker.getClass().toString());
			if (ll != walker.getClass()) {
			    System.out.println(ll.toString() + " != " + walker.getClass().toString());
			}
			else {
			    System.out.println(ll.toString() + " == " + walker.getClass().toString());
			    found_flag = true;
			    fg = true;
			    walkers.remove(walker);
			    break;
			}
		    }
		    if (!found_flag) {
			System.out.println(ll.toString() + " not found");
			break;
		    }
		}
	    }
	    if (!found_flag) {
		unreachableWalkers.add(cell);
	    }
	}
	for (Cell cell : unreachableWalkers) {
	    table.remove(cell);
	}
	System.out.println("Ende1");
	if (fg)
	    return deleteUnreachableWalkers(walkers);
	else {
	    return false;
	}
    }

    // Вроде работает верно
    private static int indexOf(Class cl) {
	for (int i = 0; i < treeTable.size(); i++) {
	    if (treeTable.get(i).getWalker() == cl) {
		return i;
	    }
	}
	return -1;
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