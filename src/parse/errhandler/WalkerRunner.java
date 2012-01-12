package parse.errhandler;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.SyntaxTree;

public class WalkerRunner {
    private List<TreeWalker> walkerList;
    private ErrorHandler errh;
    private SyntaxTree tree;
//    private Logger logger = Logger.getLogger(WalkerRunner.class);

    public WalkerRunner(ErrorHandler errh, SyntaxTree tree) {
	this.errh = errh;
	this.tree = tree;
    }

    {
	walkerList = new LinkedList<TreeWalker>();
    }

    public void run() {
	TreeWalkerTable.getListOfPossibleWalkers(walkerList);
	for (TreeWalker tw : walkerList)
	    if (TreeWalkerTable.tableContains(tw.getClass())) {
		tree.accept(tw);
		for (ErrorType et : errh.getErrors())
		    TreeWalkerTable.removeByError(et);
	    }
    }

    public void add(TreeWalker walker) {
	walkerList.add(walker);
    }
}