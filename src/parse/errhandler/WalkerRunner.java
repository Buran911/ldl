package parse.errhandler;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.SyntaxTree;

public class WalkerRunner {
    private List<TreeWalker> walkerList;
    private ErrorHandler errh;
    private SyntaxTree tree;

    // private Logger logger = Logger.getLogger(WalkerRunner.class);

    public WalkerRunner(ErrorHandler errh, SyntaxTree tree) {
	this.errh = errh;
	this.tree = tree;
    }

    {
	walkerList = new LinkedList<TreeWalker>();
    }

    public void run() {

	List<TreeWalker> tempList = new LinkedList<TreeWalker>();
	for (TreeWalker tw : walkerList)
	    tempList.add(tw);

	TreeWalkerTable twt = TreeWalkerTable.getInstance(walkerList);
	int i = 0;
	Cell cell = new Cell();
	ErrorHandler errh2 = errh.clone();
	while (i < twt.table.size()) {
	    cell = twt.table.get(i);
	    System.out.println("cell - " + cell.getWalker().getSimpleName());
	    twt.table.remove(0);
	    for (TreeWalker tw : walkerList) {
		System.out.println("tw - " + tw.getClass().getSimpleName());
		if (cell.getWalker() == tw.getClass()) {

		    tree.accept(tw);
		    tempList.remove(tw);
		    twt.setWalkers(tempList);
		    
		    if (!errh2.equals(errh)) {
			for (ErrorType et : errh.getErrors())
			    twt.removeByError(et);
			errh2 = errh.clone();
		    }
		    break;
		}
	    }
	    walkerList = tempList;
	}
	// boolean success = false;
	//
	// while (!success) {
	// for (Cell cell : twt.table) {
	// for (TreeWalker tw : walkerList) {
	// if (cell.getWalker() == tw.getClass()) {
	// tree.accept(tw);
	// }
	// }
	// }
	// }

	// while (iter.hasNext()) {
	// Cell cell = iter.next();
	// System.out.println("--- " + cell.getWalker().getSimpleName());
	// for (TreeWalker tw : walkerList) {
	// System.out.println("	 --- " + tw.getClass().getSimpleName());
	// if (cell.getWalker() == tw.getClass()) {
	// System.out.println(tw.getClass().getSimpleName() + " work");
	// tree.accept(tw);
	// if (!errh.equals(errh2)) {
	// for (ErrorType et : errh.getErrors()) {
	// twt.removeByError(et);
	// iter = twt.table.iterator();
	// }
	// errh2 = errh.clone();
	// }
	// break;
	// }
	// }
	// }
    }

    public void add(TreeWalker walker) {
	walkerList.add(walker);
    }
}