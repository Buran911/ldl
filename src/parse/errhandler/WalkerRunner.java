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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import parse.syntaxtree.SyntaxTree;

public class WalkerRunner {
    private List<TreeWalker> walkerList;
    private ErrorHandler errh;
    ErrorHandler errhCl;
    private SyntaxTree tree;
    Map<Class<? extends TreeWalker>, Row> ORDER;
    TreeWalkerTable twt;

    public WalkerRunner(ErrorHandler errh, SyntaxTree tree) {
	this.errh = errh;
	this.tree = tree;
    }

    {
	walkerList = new LinkedList<TreeWalker>();

	ORDER = new HashMap<Class<? extends TreeWalker>, Row>();
	ORDER.put(PositionEstimater.class, new Row());
	ORDER.put(FunctionalImplementedChecker.class, new Row().addPre(PositionEstimater.class).addErr(ErrorType.NotImplementedYet).Checker());
	ORDER.put(IdTableMaker.class, new Row().addPre(PositionEstimater.class));
	ORDER.put(IdRedefinedChecker.class, new Row().addPre(PositionEstimater.class).addErr(ErrorType.IdentifierRedefenition).Checker());
	ORDER.put(IdNotDefinedChecker.class, new Row().addPre(IdTableMaker.class).addErr(ErrorType.IdentifierUndefined).Checker());
	ORDER.put(IdConvertor.class, new Row().addPre(IdNotDefinedChecker.class));
	ORDER.put(IdTableFiller.class, new Row().addPre(IdNotDefinedChecker.class));
	ORDER.put(TypeMismatchChecker.class, new Row().addPre(IdConvertor.class).addPre(IdTableFiller.class).addPre(IdRedefinedChecker.class).addErr(ErrorType.UncompatibleTypes)
		.Checker());
	ORDER.put(TemplateTypeFiller.class, new Row());
	ORDER.put(TemplateEqClassesFiller.class, new Row().addPre(TemplateTypeFiller.class).addPre(IdConvertor.class));

    }

    public void run() {
	twt = new TreeWalkerTable(walkerList, ORDER);
	errhCl = errh.clone();
	for (Cell cell : twt.getEndElements())
	    func(cell);
	System.out.println("end");
    }

    public void func(Cell cell) {
	if (cell.getPrecellList() != null)
	    for (Cell precell : cell.getPrecellList())
		if (precell.isAvailable()) {
		    func(precell);
		    // код для обработки
		    tree.accept(precell.getTreeWalker());
		    precell.setAvailable(false);
		    List<ErrorType> listError = errh.difference(errhCl);
		}
	System.out.println("end");
    }

    public void add(TreeWalker walker) {
	walkerList.add(walker);
    }
}