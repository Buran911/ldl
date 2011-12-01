package parse.errhandler;

import generation.walkers.TreeWalker;

import java.util.LinkedList;

import parse.syntaxtree.SyntaxTree;

public class WalkerRunner {
    private LinkedList<TreeWalker> walkerList;
    private ErrorHandler errh;
    private SyntaxTree tree;

    public WalkerRunner(ErrorHandler errh, SyntaxTree tree) {
	this.errh = errh;
	this.tree = tree;
    }

    {
	walkerList = new LinkedList<TreeWalker>();
    }

    public void run() {
	for (TreeWalker walker : walkerList) {
	    if (walker instanceof Checker) {
		if (errh.isPermitted(((Checker) walker).getErrorTypes())) {
		    tree.accept(walker);
		}else{
		    errh.printErrors();
		    throw new RuntimeException();
		}
	    }
	    else {
		tree.accept(walker);
	    }
	}
    }

    public void add(TreeWalker walker) {
	walkerList.add(walker);
    }
}