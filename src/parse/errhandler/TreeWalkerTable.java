package parse.errhandler;

import generation.walkers.TreeWalker;
import generation.walkers.walkers.FunctionalImplementedChecker;
import generation.walkers.walkers.IdConvertor;
import generation.walkers.walkers.IdNotDefinedChecker;
import generation.walkers.walkers.IdRedefinedChecker;
import generation.walkers.walkers.IdTableFiller;
import generation.walkers.walkers.IdTableMaker;
import generation.walkers.walkers.PositionEstimater;
import generation.walkers.walkers.TemplateEqClassesFiller;
import generation.walkers.walkers.TemplateTypeFiller;
import generation.walkers.walkers.TypeMismatchChecker;

import java.util.LinkedList;
import java.util.List;

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

    public static List<TreeWalker> getRealWalkers() {
	return realWalkers;
    }

    public static void setRealWalkers(List<TreeWalker> realWalkers) {
	TreeWalkerTable.realWalkers = realWalkers;
    }

    public static boolean tableContains(Class<? extends TreeWalker> classe) {
	for (Cell cell : table)
	    if (cell.getWalker() == classe)
		return true;
	return false;
    }

    static {
	TREETABLE = new LinkedList<Cell>();

	TREETABLE.add(new Cell(PositionEstimater.class));
	TREETABLE.add(new Cell(FunctionalImplementedChecker.class).addPre(PositionEstimater.class));
	TREETABLE.add(new Cell(IdTableMaker.class).addPre(PositionEstimater.class));
	TREETABLE.add(new Cell(IdRedefinedChecker.class).addPre(PositionEstimater.class));
	TREETABLE.add(new Cell(IdNotDefinedChecker.class).addPre(IdTableMaker.class));
	TREETABLE.add(new Cell(IdTableFiller.class).addPre(IdNotDefinedChecker.class).addErr(ErrorType.IdentifierUndefined));
	TREETABLE.add(new Cell(IdConvertor.class).addPre(IdNotDefinedChecker.class).addErr(ErrorType.IdentifierUndefined));
	TREETABLE.add(new Cell(TypeMismatchChecker.class).addPre(IdTableFiller.class).addPre(IdConvertor.class).addPre(IdRedefinedChecker.class)
		.addErr(ErrorType.IdentifierRedefenition).addErr(ErrorType.IdentifierUndefined));
	TREETABLE.add(new Cell(TemplateEqClassesFiller.class).addPre(TypeMismatchChecker.class).addErr(ErrorType.IdentifierRedefenition).addErr(ErrorType.IdentifierUndefined));
	TREETABLE.add(new Cell(TemplateTypeFiller.class).addPre(TypeMismatchChecker.class).addErr(ErrorType.IdentifierRedefenition).addErr(ErrorType.IdentifierUndefined));
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
	for (TreeWalker tw : treeWalkerTable.getCleanListOfWalkers(walkers)) 
	    returnList.add(tw.getClass());
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
	List<Cell> tempTable = new LinkedList<Cell>();
	List<TreeWalker> tempWalkers = new LinkedList<TreeWalker>();
	for (TreeWalker temp : realWalkers) {
	    Cell cell = new Cell();
	    cell = getCellByClass(temp.getClass(), table);
	    if (!cell.getErrors().contains(errorType)) {
		tempTable.add(cell);
		tempWalkers.add(temp);
		result = true;
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
	for (int i = 0; i < TREETABLE.size(); i++) 
	    if (TREETABLE.get(i).getWalker() == cl) 
		return i;
	return -1;
    }
}