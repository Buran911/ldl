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

public class TreeWalkerTable {
    public static final List<Cell> TREETABLE;// “аблица где все
				       // зависимости
				       // walker'ов (одна дл€ всех)
    private List<Cell> table;

    private final List<TreeWalker> walkers;

    boolean errorCheck = false;

    static {
	TREETABLE = new LinkedList<Cell>();

	TREETABLE.add(new Cell(PositionEstimater.class).addPost(FunctionalImplementedChecker.class).addPost(IdTableMaker.class).addPost(IdRedefinedChecker.class));
	TREETABLE.add(new Cell(FunctionalImplementedChecker.class).addPre(PositionEstimater.class).setChecker(true));
	TREETABLE.add(new Cell(IdTableMaker.class).addPre(PositionEstimater.class).addPost(IdNotDefinedChecker.class));
	TREETABLE.add(new Cell(IdRedefinedChecker.class).addPre(PositionEstimater.class).setChecker(true));
	TREETABLE.add(new Cell(IdNotDefinedChecker.class).addPre(IdTableMaker.class).addPost(IdConvertor.class).addPost(IdTableFiller.class).setChecker(true));
	TREETABLE.add(new Cell(IdTableFiller.class).addPre(IdNotDefinedChecker.class).addPost(TypeMismatchChecker.class).addErr(ErrorType.IdentifierUndefined));
	TREETABLE.add(new Cell(IdConvertor.class).addPre(IdNotDefinedChecker.class).addPost(TemplateEqClassesFiller.class).addErr(ErrorType.IdentifierUndefined));
	TREETABLE.add(new Cell(TypeMismatchChecker.class).addPre(IdTableFiller.class).addPre(IdConvertor.class).addPre(IdRedefinedChecker.class)
		.addErr(ErrorType.IdentifierRedefenition).addErr(ErrorType.IdentifierUndefined).setChecker(true));
	TREETABLE.add(new Cell(TemplateTypeFiller.class).addPost(TemplateEqClassesFiller.class));
	TREETABLE.add(new Cell(TemplateEqClassesFiller.class).addPre(TemplateTypeFiller.class).addPre(IdConvertor.class));

    }

    {
	table = new LinkedList<Cell>();
    }

    private TreeWalkerTable(List<TreeWalker> walkers) {
	this.walkers = walkers;
    }

    public static TreeWalkerTable getInstance(List<TreeWalker> walkers) {
	TreeWalkerTable twt = new TreeWalkerTable(walkers);
	for (TreeWalker walker : walkers) {
	    System.out.println(" -- " + walker.getClass().getSimpleName());
	    if (walker instanceof Checker) {
		twt.errorCheck = true;
		break;
	    }
	}

	if (twt.errorCheck) {
	    twt.deleteUnusedWalkers();
	    twt.deleteUnreachableWalkers();
	}
	else {
	    List<Cell> tempTable = new LinkedList<Cell>();
	    for(Cell cell : TREETABLE){
		twt.table.add(cell.clone());
	    }
	    for (Cell cell : twt.table) {
		System.out.println("cell = " + cell.getWalker().getSimpleName());
		if (cell.isChecker()) {
		    //pre
		    for (Class<? extends TreeWalker> classe : cell.getPreproc()) {
			System.out.println("classe = " + classe.getSimpleName());
			Cell precell = twt.getCellByClass(classe, twt.table);
			precell.remPost();
			for (Class<? extends TreeWalker> postclass : cell.getPostproc()) {
			    System.out.println("postclass = " + postclass.getSimpleName());
			    precell.addPost(postclass);
			}
		    }
		    
		    //post
		    for (Class<? extends TreeWalker> classe : cell.getPostproc()) {
			System.out.println("classe = " + classe.getSimpleName());
			Cell postcell = twt.getCellByClass(classe, twt.table);

			postcell.remPre();
			for (Class<? extends TreeWalker> preclass : cell.getPreproc()) {
			    System.out.println("postclass = " + preclass.getSimpleName());
			    postcell.addPre(preclass);
			}
			
			
		    }
		    
		    
		}else{
		    tempTable.add(cell);
		}
	    }
	    System.out.println("ende");
	}
	twt.sortList();

	return twt;
    }

    private void sortList() {
	List<Cell> tempTable = new LinkedList<Cell>();
	for (Cell cell : TREETABLE)
	    if (table.contains(cell))
		tempTable.add(cell);
	table = tempTable;
    }

    public boolean tableContains(Class<? extends TreeWalker> classe) {
	for (Cell cell : TREETABLE)
	    if (cell.getWalker() == classe)
		return true;
	return false;
    }

    private void deleteUnusedWalkers() {
	for (TreeWalker walker : walkers) {
	    int position = indexOf(walker.getClass());
	    table.add(TREETABLE.get(position));
	}
    }

    private void deleteUnreachableWalkers() {
	List<Cell> unreachableCells;
	unreachableCells = new LinkedList<Cell>();
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
	List<Class<? extends TreeWalker>> predecessors = cell.getPreproc();

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

    public boolean removeByError(ErrorType errorType) {
	boolean result = false;

	for (TreeWalker temp : walkers) {

	    Cell cell = new Cell();

	    cell = getCellByClass(temp.getClass(), table);

	    if (cell.getErrors().contains(errorType)) {

		cell.setPassed(true);

		result = true;

	    }
	}
	return result;
    }

    // ¬роде работает верно
    /**
     * ¬озвращает номер строки walker'а в treeTable . ≈сли walker не найден
     * возвращает -1
     */
    private static int indexOf(Class<? extends TreeWalker> cl) {
	for (int i = 0; i < TREETABLE.size(); i++)
	    if (TREETABLE.get(i).getWalker() == cl)
		return i;
	return -1;
    }

    public List<Cell> getTable() {
	return table;
    }
}