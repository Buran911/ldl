package parse.errhandler;

import generation.idtable.IdTable;
import generation.walkers.TreeWalker;
import generation.walkers.strategys.BottomUpWalkingStrategy;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.FunctionalImplementedChecker;
import generation.walkers.walkers.IdConvertor;
import generation.walkers.walkers.IdNotDefinedChecker;
import generation.walkers.walkers.IdRedefinedChecker;
import generation.walkers.walkers.IdTableFiller;
import generation.walkers.walkers.IdTableMaker;
import generation.walkers.walkers.PositionEstimater;
import generation.walkers.walkers.TypeMismatchChecker;

import java.util.Iterator;
import java.util.LinkedList;

import parse.syntaxtree.SyntaxTree;

public class WalkerRunner {
    private LinkedList<TreeWalker> walkerList;
    private ErrorHandler errh;
    private IdTable idTable;
    private SyntaxTree tree;

    public WalkerRunner(ErrorHandler errh, IdTable idTable, SyntaxTree tree) {
	this.errh = errh;
	this.idTable = idTable;
	this.tree = tree;
    }

    {
	walkerList = new LinkedList<TreeWalker>();
    }

    public void run() {
	for (TreeWalker walker : walkerList) {
	    if (walker instanceof Int) {
		if (errh.isPermitted(((Int) walker).getErrorTypes())) {
		    tree.accept(walker);
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
