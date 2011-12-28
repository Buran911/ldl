package parse.errhandler;

import generation.walkers.TreeWalker;
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

public class TreeWalkerTable {
    public final static LinkedList<Cell> TREETABLE;// “аблица где все
						   // зависимости
						   // walker'ов (одна дл€ всех)
    public List<Cell> table; // —писок walker'ов , уоторые будут использоватьст€
			     // в программе
    private LinkedList<Cell> unreachableCells;

    static {
	TREETABLE = new LinkedList<Cell>();

	TREETABLE.add(new Cell(PositionEstimater.class, null, new LinkedList<ErrorType>()));

	LinkedList<Class<? extends TreeWalker>> fic = new LinkedList<Class<? extends TreeWalker>>();
	fic.add(PositionEstimater.class);
	TREETABLE.add(new Cell(FunctionalImplementedChecker.class, fic, new LinkedList<ErrorType>()));

	LinkedList<Class<? extends TreeWalker>> tm = new LinkedList<Class<? extends TreeWalker>>();
	tm.add(PositionEstimater.class);
	TREETABLE.add(new Cell(IdTableMaker.class, tm, new LinkedList<ErrorType>()));

	LinkedList<Class<? extends TreeWalker>> irc = new LinkedList<Class<? extends TreeWalker>>();
	irc.add(PositionEstimater.class);
	TREETABLE.add(new Cell(IdRedefinedChecker.class, irc, new LinkedList<ErrorType>()));

	LinkedList<Class<? extends TreeWalker>> indc = new LinkedList<Class<? extends TreeWalker>>();
	indc.add(IdTableMaker.class);
	TREETABLE.add(new Cell(IdNotDefinedChecker.class, indc, new LinkedList<ErrorType>()));

	LinkedList<Class<? extends TreeWalker>> itf = new LinkedList<Class<? extends TreeWalker>>();
	itf.add(IdNotDefinedChecker.class);
	TREETABLE.add(new Cell(IdTableFiller.class, itf, new LinkedList<ErrorType>()));

	LinkedList<Class<? extends TreeWalker>> id = new LinkedList<Class<? extends TreeWalker>>();
	id.add(IdNotDefinedChecker.class);
	TREETABLE.add(new Cell(IdConvertor.class, id, new LinkedList<ErrorType>()));

	LinkedList<Class<? extends TreeWalker>> tmc = new LinkedList<Class<? extends TreeWalker>>();
	tmc.add(IdTableFiller.class);
	tmc.add(IdConvertor.class);
	tmc.add(IdRedefinedChecker.class);
	LinkedList<ErrorType> tmce = new LinkedList<ErrorType>();
	tmce.add(ErrorType.IdentifierRedefenition);
	tmce.add(ErrorType.IdentifierUndefined);
	TREETABLE.add(new Cell(TypeMismatchChecker.class, tmc, tmce));
    }

    {
	table = new LinkedList<Cell>();

	unreachableCells = new LinkedList<Cell>();
    }

    private TreeWalkerTable() {
    }

    private List<TreeWalker> cleanWalkers(List<TreeWalker> walkers) {

	List<TreeWalker> walkersTemporary = new LinkedList<TreeWalker>();

	for (TreeWalker walker : walkers)
	    for (Cell cell : table)
		if (walker.getClass() == cell.getWalker())
		    walkersTemporary.add(walker);
	walkers = walkersTemporary;
	return walkersTemporary;
    }

    public static List<TreeWalker> getCleanTreeWalkerList(List<TreeWalker> walkers) {
	TreeWalkerTable twt = new TreeWalkerTable();
	twt.deleteUnusedWalkers(walkers);
	twt.deleteUnreachableWalkers();
	return twt.cleanWalkers(walkers);
    }
    public static List<Cell> getCleanTable(List<TreeWalker> walkers){
	TreeWalkerTable twt = new TreeWalkerTable();
	twt.deleteUnusedWalkers(walkers);
	twt.deleteUnreachableWalkers();
	return twt.table;
    }

    private void deleteUnusedWalkers(List<TreeWalker> walkers) {
	for (TreeWalker walker : walkers) {
	    int position = indexOf(walker.getClass());
	    table.add(TREETABLE.get(position));
	}
    }

    private void deleteUnreachableWalkers() {
	for (Cell cell : table)
	    if (!isAbleToPerform(cell, table))
		unreachableCells.add(cell);
	for (Cell cell : unreachableCells)
	    table.remove(cell);
    }

    // TODO сделать работу типов по стандартным методам
    /** ѕолучение Cell из таблицы зна€ только Class */
    private Cell getCellByClass(Class<? extends TreeWalker> classe, List<Cell> table) {
	for (Cell cell : table)
	    if (cell.getWalker() == classe)
		return cell;
	return null;
    }

    /** провер€етс€ , возможен запуск этого cell в данной таблице */
    private boolean isAbleToPerform(Cell cell, List<Cell> table) {
	if (cell == null)
	    return false;
	List<Class<? extends TreeWalker>> predecessors = new LinkedList<Class<? extends TreeWalker>>();
	predecessors = cell.getPreprocesses();
	if (predecessors == null)
	    return true;
	else {
	    boolean return_flag = true;
	    for (Class<? extends TreeWalker> predecessor : predecessors)
		if (!isAbleToPerform(getCellByClass(predecessor, table), table))
		    return_flag = false;
	    return return_flag;
	}
    }

    // ¬роде работает верно
    /**
     * ¬озвращает номер строки walker'а в treeTable . ≈сли walker не найден
     * возвращает -1
     */
    private static int indexOf(Class<? extends TreeWalker> cl) {
	for (int i = 0; i < TREETABLE.size(); i++) {
	    if (TREETABLE.get(i).getWalker() == cl) {
		return i;
	    }
	}
	return -1;
    }
}