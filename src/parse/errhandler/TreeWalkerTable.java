package parse.errhandler;

import generation.walkers.TreeWalker;
import generation.walkers.walkers.TemplateEqClassesFiller;
import generation.walkers.walkers.TemplateTypeFiller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class TreeWalkerTable {
    private Map<Class<? extends TreeWalker>, Row> TREEMAP;
    private final List<TreeWalker> walkers;
    private boolean errorCheck = false;
    public Map<Class<? extends TreeWalker>, Cell> cellList;
    private Logger logger = Logger.getLogger(TreeWalkerTable.class);

    {
	cellList = new HashMap<Class<? extends TreeWalker>, Cell>();
    }

    private void initialization() {
	// Создание реестра ячеек (cell'ы не связаны между собой)
	for (Class<? extends TreeWalker> classe : TREEMAP.keySet())
	    if (cellList.get(classe) == null) {
		cellList.put(classe, new Cell(classe));
		for (Class<? extends TreeWalker> preclass : TREEMAP.get(classe).getDependList())
		    if (cellList.get(preclass) == null)
			cellList.put(preclass, new Cell(preclass));
	    }
	// Создание двусторонних связей
	for (Class<? extends TreeWalker> classe : TREEMAP.keySet()) {
	    Row rw = TREEMAP.get(classe);
	    Cell cell = cellList.get(classe);
	    cell.setChecker(rw.isChecker());
	    for (Class<? extends TreeWalker> post : rw.getDependList())
		cell.addPre(cellList.get(post));
	    for (Cell precell : cell.getPrecellList())
		precell.addPost(cell);
	}
	for (Class<? extends TreeWalker> classe : TREEMAP.keySet()) {
	    Cell cell = cellList.get(classe);
	    for (ErrorType error : TREEMAP.get(classe).getErrorList())
		cell.addErr(error);
	}
    }

    private void rebuild() {
	// В соответствии с задачей перестраиваем зависимости
	for (TreeWalker walker : walkers)
	    if (walker instanceof Checker) {
		errorCheck = true;
		break;
	    }
	logger.debug("errorCheck : " + errorCheck);
	if (errorCheck) {
	    // Удалить TemplateTypeFiller и TemplateEqClassesFiller
	    cellList.get(TemplateTypeFiller.class).disappear();
	    cellList.get(TemplateEqClassesFiller.class).disappear();
	}
	else {
	    // Удалить Checker'ы и перестроить связи
	    System.out.println("Удаляем связи");
	    for (Class<? extends TreeWalker> classe : TREEMAP.keySet()) {
		Cell cell = cellList.get(classe);
		if (cell.isChecker())
		    cell.disappear();
	    }
	}
    }

    private void fill() {
	// Заполняем клетки walker'ами
	for (TreeWalker walker : walkers)
	    cellList.get(walker.getClass()).setTreeWalker(walker);
    }

    private void clean() {
	// Чистим клетки (оставляем только те walker'ы , которые могут
	// теоретически запуститься)
	for (Class<? extends TreeWalker> classe : cellList.keySet()) {
	    Cell cell = cellList.get(classe);
	    if ((cell.getTreeWalker() == null) && cell.isAvailable()) {
		logger.debug("cell - " + cell.getWalkerClass().getSimpleName() + " и после на удаление");
		cell.delSubTree(cell);
	    }
	}
    }

    public TreeWalkerTable(List<TreeWalker> walkers, Map<Class<? extends TreeWalker>, Row> treeMap) {
	this.TREEMAP = treeMap;
	this.walkers = walkers;
	initialization();
	rebuild();
	fill();
	clean();
    }

    // Получение элементов , с которых можно начать обход (верхние)
    public List<Cell> getStartElements() {
	List<Cell> tempList = new LinkedList<Cell>();
	for (Class<? extends TreeWalker> classe : cellList.keySet()) {
	    Cell cell = cellList.get(classe);
	    if ((cell.isAvailable()) && cell.getPrecellList().size() == 0)
		tempList.add(cell);
	}
	return tempList;
    }

    // Получение элементов , с которых можно начать обход (низ)
    public List<Cell> getEndElements() {
	List<Cell> tempList = new LinkedList<Cell>();
	for (Class<? extends TreeWalker> classe : cellList.keySet()) {
	    Cell cell = cellList.get(classe);
	    if ((cell.isAvailable()) && (cell.getPostcellList() != null) && (cell.getPostcellList().size() == 0))
		tempList.add(cell);
	}
	return tempList;
    }

    public void removeByError(ErrorType error) {
	for (Class<? extends TreeWalker> classe : cellList.keySet()) {
	    Cell cell = cellList.get(classe);
	    if ((cell.isAvailable()) && cell.getErrors().contains(error)) {
		logger.debug("cell - " + cell.getWalkerClass().getSimpleName());
		cell.delSubTree();
	    }
	}
    }
}