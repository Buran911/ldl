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

import antlr.collections.impl.LList;

//TODO Сделать запуск walker'ов исходя из списка возможных для запуска и возможность вычеркивать волкеров из списка возможных

public class TreeWalkerTable {
    public final static List<Cell> TREETABLE;// Таблица где все
					     // зависимости
					     // walker'ов (одна для всех)
    public static List<Cell> table; // Список walker'ов , которые будут
				    // использоватьстя
				    // в программе
    private List<Cell> unreachableCells;

    static List<TreeWalker> realWalkers;
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
	LinkedList<ErrorType> itfe = new LinkedList<ErrorType>();
	itfe.add(ErrorType.IdentifierUndefined);
	TREETABLE.add(new Cell(IdTableFiller.class, itf, itfe));

	LinkedList<Class<? extends TreeWalker>> id = new LinkedList<Class<? extends TreeWalker>>();
	id.add(IdNotDefinedChecker.class);
	LinkedList<ErrorType> ide = new LinkedList<ErrorType>();
	ide.add(ErrorType.IdentifierUndefined);
	TREETABLE.add(new Cell(IdConvertor.class, id, ide));

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

	realWalkers = new LinkedList<TreeWalker>();
    }

    private TreeWalkerTable() {
    }

    public static List<Class<? extends TreeWalker>> getListOfPossibleWalkers(List<TreeWalker> walkers) {
	TreeWalkerTable treeWalkerTable = new TreeWalkerTable();
	List<Class<? extends TreeWalker>> returnList = new LinkedList<Class<? extends TreeWalker>>();

	treeWalkerTable.deleteUnusedWalkers(walkers);
	treeWalkerTable.deleteUnreachableWalkers();

	for (TreeWalker tw : treeWalkerTable.getCleanListOfWalkers(walkers)) {
	    returnList.add(tw.getClass());
	}

	return returnList;
    }

    private List<TreeWalker> getCleanListOfWalkers(List<TreeWalker> walkers) {
	for (TreeWalker walker : walkers)
	    for (Cell cell : table)
		if (walker.getClass() == cell.getWalker())
		    realWalkers.add(walker);
	walkers = realWalkers;
	return realWalkers;
    }

    public static List<TreeWalker> getCleanTreeWalkerList(List<TreeWalker> walkers) {
	TreeWalkerTable twt = new TreeWalkerTable();
	twt.deleteUnusedWalkers(walkers);
	twt.deleteUnreachableWalkers();
	return twt.getCleanListOfWalkers(walkers);
    }

    public static List<Cell> getCleanTable(List<TreeWalker> walkers) {
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
    /** Получение Cell из таблицы зная только Class */
    private static Cell getCellByClass(Class<? extends TreeWalker> classe, List<Cell> table) {
	for (Cell cell : table)
	    if (cell.getWalker() == classe)
		return cell;
	return null;
    }

    /** проверяется , возможен запуск этого cell в данной таблице */
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

    public static boolean removeByError(ErrorType errorType) {
	boolean result = false;
	// 1.Найти элемент по ошибке
	// Необходимые ингридиенты : ошибка , список walker'ов которые реально
	// запускаются
	// Необходимо найти все классы , на которые влияет ошибка
	//
	List<Cell> tempTable = new LinkedList<Cell>();
	List<TreeWalker> tempWalkers = new LinkedList<TreeWalker>();
	
	
	// static List<TreeWalker> realWalkers;
	for (TreeWalker temp : realWalkers) {

	    Cell cell = new Cell();
	    cell = getCellByClass(temp.getClass(), table);

	    if (!cell.getErrors().contains(errorType)) {
		tempTable.add(cell);
		tempWalkers.add(temp);
		result = true;
	    }
	    else {
		System.out.println(cell.getWalker() + " deleted");
	    }
	}
	table = tempTable;
	realWalkers = tempWalkers;
	return result;
    }

    // Вроде работает верно
    /**
     * Возвращает номер строки walker'а в treeTable . Если walker не найден
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