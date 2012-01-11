package parse.errhandler;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import parse.syntaxtree.SyntaxTree;
import application.util.Halt;

public class WalkerRunner {
    private List<TreeWalker> walkerList;
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
	
	List<Class<? extends TreeWalker>> LS = TreeWalkerTable.getListOfPossibleWalkers(walkerList);
	
	logger.debug("run() started");

	logger.debug("run() finished");
    }

    public void add(TreeWalker walker) {
	walkerList.add(walker);
    }
}