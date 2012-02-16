package parse.errhandler;

import generation.walkers.TreeWalker;
import generation.walkers.walkers.TemplateEqClassesFiller;
import generation.walkers.walkers.TemplateTypeFiller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/** ������� walker'�� , ����������� ��������� ��� ���������� */
public class TreeWalkerTable {
    // ����� ������������ ����� (������ , ���������)
    private Map<Class<? extends TreeWalker>, Row> TREEMAP;
    // ������ walker'�� , ������� ����� ���������
    private final List<TreeWalker> walkers;
    private boolean errorCheck = false;
    // ������� ������ ������� ��������� ������� -> ������� �������������� walker
    public Map<Class<? extends TreeWalker>, Cell> cellList;
    private Logger logger = Logger.getLogger(TreeWalkerTable.class);

    {
	cellList = new HashMap<Class<? extends TreeWalker>, Cell>();
    }

    private void initialization() {
	// �������� ������� ����� (cell'� �� ������� ����� �����)
	for (Class<? extends TreeWalker> classe : TREEMAP.keySet())
	    if (cellList.get(classe) == null) {
		cellList.put(classe, new Cell(classe));
		for (Class<? extends TreeWalker> preclass : TREEMAP.get(classe).getDependList())
		    if (cellList.get(preclass) == null)
			cellList.put(preclass, new Cell(preclass));
	    }
	// �������� ������������ ������
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
	// � ������������ � ������� ������������� �����������
	for (TreeWalker walker : walkers)
	    if (walker instanceof Checker) {
		errorCheck = true;
		break;
	    }
	logger.debug("errorCheck : " + errorCheck);
	if (errorCheck) {
	    // ������� TemplateTypeFiller � TemplateEqClassesFiller
	    cellList.get(TemplateTypeFiller.class).disappear();
	    cellList.get(TemplateEqClassesFiller.class).disappear();
	}
	else {
	    // ������� Checker'� � ����������� �����
	    System.out.println("������� �����");
	    for (Class<? extends TreeWalker> classe : TREEMAP.keySet()) {
		Cell cell = cellList.get(classe);
		if (cell.isChecker())
		    cell.disappear();
	    }
	}
	// ������� ������������ �����
	for (Class<? extends TreeWalker> classe : cellList.keySet()) {
	    Cell cell = cellList.get(classe);
	    if ((cell.getPrecellList() != null) && (cell.getPrecellList().size() != 0)) {
		System.out.println(" - " + cell.getWalkerClass().getSimpleName());
		deleteTR(cell);
	    }
	}
    }

    // ������� ������������ ����� (delete transitive relations)
    public void deleteTR(Cell cell) {
	List<Cell> cellListM = cell.getPrecellList();

	for (Cell cll : cellListM) {
	    System.out.println(" --- " + cll.getWalkerClass().getSimpleName());
	}
    }
    
    

    private void fill() {
	// ��������� ������ walker'���
	for (TreeWalker walker : walkers)
	    cellList.get(walker.getClass()).setTreeWalker(walker);
    }

    private void clean() {
	// ������ ������ (��������� ������ �� walker'� , ������� �����
	// ������������ �����������)
	for (Class<? extends TreeWalker> classe : cellList.keySet()) {
	    Cell cell = cellList.get(classe);
	    if ((cell.getTreeWalker() == null) && cell.isAvailable()) {
		logger.debug("cell - " + cell.getWalkerClass().getSimpleName() + " � ����� �� ��������");
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

    // ��������� ��������� , � ������� ����� ������ ����� (�������)
    public List<Cell> getStartElements() {
	List<Cell> tempList = new LinkedList<Cell>();
	for (Class<? extends TreeWalker> classe : cellList.keySet()) {
	    Cell cell = cellList.get(classe);
	    if ((cell.isAvailable()) && cell.getPrecellList().size() == 0)
		tempList.add(cell);
	}
	return tempList;
    }

    // ��������� ��������� , � ������� ����� ������ ����� (���)
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