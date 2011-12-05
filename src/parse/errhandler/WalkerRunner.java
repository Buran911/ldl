package parse.errhandler;

import generation.walkers.TreeWalker;

import java.util.LinkedList;

import org.apache.log4j.Logger;

import parse.syntaxtree.SyntaxTree;
import application.App;
import application.util.Halt;

public class WalkerRunner {
    private LinkedList<TreeWalker> walkerList;
    private ErrorHandler errh;
    private SyntaxTree tree;
    private Logger logger = Logger.getLogger(WalkerRunner.class);


    public WalkerRunner(ErrorHandler errh, SyntaxTree tree) {
	this.errh = errh;
	this.tree = tree;
    }

    {
	walkerList = new LinkedList<TreeWalker>();
    }

    public void run() {
	logger.debug("run() started");
	for (TreeWalker walker : walkerList) {
	    logger.debug("	walker : " + walker.toString());
	    if (walker instanceof Checker) {
		logger.debug("is Checker");	
		if (errh.isPermitted(((Checker) walker).getErrorTypes())) {
		    tree.accept(walker);
		}
	    }
	    else {
		logger.debug("is not Checker");
		if (errh.hasErrors()) {
		    logger.debug("Errors found");
		    errh.printErrors();
		    logger.debug("Halting...");
		    throw new Halt();
		}
		else {
		    logger.debug("Errors not found , starting walker");
		    tree.accept(walker);
		    logger.debug("walker finished");
		}
	    }
	}
	logger.debug("run() finished");
    }

    public void add(TreeWalker walker) {
	walkerList.add(walker);
    }
}