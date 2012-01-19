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
	TreeWalkerTable twt = TreeWalkerTable.getInstance(walkerList);
	ErrorHandler errh2 = errh.clone();
	for (Cell cell : twt.getTable()) {
	    if (cell.isPassed())
		break;
	    for (TreeWalker tw : walkerList)
		if ((cell.getWalker() == tw.getClass()) && !cell.isPassed()) {
		    System.out.println(tw.getClass().getSimpleName() + " начинает работать");
		    tree.accept(tw);
		    System.out.println(tw.getClass().getSimpleName() + " отработал");
		    cell.setPassed(true);
		    if (!errh2.equals(errh)){
			System.out.println("Найдена ошибка");
			for (ErrorType et : errh.getErrors())
			    twt.removeByError(et);
		    }
		    errh2 = errh.clone();
		    break;
		}

	}
	System.out.println();
    }
    public void add(TreeWalker walker) {
	walkerList.add(walker);
    }
}