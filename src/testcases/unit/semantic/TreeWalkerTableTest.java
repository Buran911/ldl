package testcases.unit.semantic;

import generation.templateengine.QueryConstraints;
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
import generation.walkers.walkers.TemplateEqClassesFiller;
import generation.walkers.walkers.TemplateTypeFiller;
import generation.walkers.walkers.TypeMismatchChecker;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import parse.errhandler.Cell;
import parse.errhandler.ErrorHandler;
import parse.errhandler.ErrorType;
import parse.errhandler.ParseError;
import parse.errhandler.Row;
import parse.errhandler.TreeWalkerTable;
import parse.util.Source;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell1;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell10;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell11;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell12;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell13;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell14;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell15;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell16;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell2;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell3;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell4;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell5;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell6;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell7;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell8;
import testcases.unit.semantic.TreeWalkerTableTestClasses.testcell9;

public class TreeWalkerTableTest {
    private Logger logger = Logger.getLogger(TreeWalkerTableTest.class);

    @SuppressWarnings("unused")
    @Test
    public void ErrorTest() {
	ErrorHandler er1 = new ErrorHandler(new Source("testdata/compatibility/file.ldl"));
	er1.addError(new ParseError(ErrorType.IdentifierUndefined, 1, 1, "Чёта не так"));
	er1.addError(new ParseError(ErrorType.IdentifierRedefenition, 2, 2, "Ашыбка пыщ-пыщ"));
	ErrorHandler er2 = er1.clone();
	er1.addError(new ParseError(ErrorType.UncompatibleTypes, 2, 2, "Тру-ту-ту"));
	List<ErrorType> listError = er1.difference(er2);
	logger.debug("ende");
    }

    @Test
    public void Тест() {

	Map<Class<? extends TreeWalker>, Row> TREETABLE = new HashMap<Class<? extends TreeWalker>, Row>();
	TREETABLE.put(PositionEstimater.class, new Row());
	TREETABLE.put(FunctionalImplementedChecker.class, new Row().addPre(PositionEstimater.class).addErr(ErrorType.NotImplementedYet).Checker());
	TREETABLE.put(IdTableMaker.class, new Row().addPre(PositionEstimater.class));
	TREETABLE.put(IdRedefinedChecker.class, new Row().addPre(PositionEstimater.class).addErr(ErrorType.IdentifierRedefenition).Checker());
	TREETABLE.put(IdNotDefinedChecker.class, new Row().addPre(IdTableMaker.class).addErr(ErrorType.IdentifierUndefined).Checker());
	TREETABLE.put(IdConvertor.class, new Row().addPre(IdNotDefinedChecker.class));
	TREETABLE.put(IdTableFiller.class, new Row().addPre(IdNotDefinedChecker.class));
	TREETABLE.put(TypeMismatchChecker.class,
		new Row().addPre(IdConvertor.class).addPre(IdTableFiller.class).addPre(IdRedefinedChecker.class).addErr(ErrorType.UncompatibleTypes).Checker());
	TREETABLE.put(TemplateTypeFiller.class, new Row());
	TREETABLE.put(TemplateEqClassesFiller.class, new Row().addPre(TemplateTypeFiller.class).addPre(IdConvertor.class));

	List<TreeWalker> walkersChecker = new LinkedList<TreeWalker>();
	walkersChecker.add(new TypeMismatchChecker(new IdParsigStrategy(), null));
	walkersChecker.add(new FunctionalImplementedChecker(new BottomUpWalkingStrategy(), null));
	walkersChecker.add(new IdRedefinedChecker(new IdParsigStrategy(), null));
	walkersChecker.add(new IdTableMaker(new IdParsigStrategy(), null));
	walkersChecker.add(new IdTableFiller(new IdParsigStrategy(), null));
	walkersChecker.add(new IdNotDefinedChecker(new IdParsigStrategy(), null, null));
	walkersChecker.add(new IdConvertor(new IdParsigStrategy(), null));
	walkersChecker.add(new PositionEstimater(new IdParsigStrategy()));

	List<TreeWalker> walkers = new LinkedList<TreeWalker>();
	walkers.add(new IdTableMaker(new IdParsigStrategy(), null));
	walkers.add(new PositionEstimater(new IdParsigStrategy()));
	walkers.add(new IdTableFiller(new IdParsigStrategy(), null));
	walkers.add(new TemplateTypeFiller());
	walkers.add(new IdConvertor(new IdParsigStrategy(), null));
	walkers.add(new TemplateEqClassesFiller(new QueryConstraints()));

	TreeWalkerTable twt = new TreeWalkerTable(walkersChecker, TREETABLE);
	// TreeWalkerTable twt = new TreeWalkerTable(walkers, TREETABLE);
	for (Cell cell : twt.getStartElements()) {

	}

	twt.removeByError(ErrorType.IdentifierRedefenition);
	logger.debug("Ende");
    }

    @SuppressWarnings("unused")
    @Test
    public void ТестНа16_1() {
	Map<Class<? extends TreeWalker>, Row> classList = new HashMap<Class<? extends TreeWalker>, Row>();
	classList.put(testcell1.class, new Row());
	classList.put(testcell2.class, new Row());
	classList.put(testcell3.class, new Row());
	classList.put(testcell4.class, new Row());
	classList.put(testcell5.class, new Row().addPre(testcell1.class).addPre(testcell2.class));
	classList.put(testcell6.class, new Row().addPre(testcell1.class).addPre(testcell2.class).addPre(testcell3.class));
	classList.put(testcell7.class, new Row().addPre(testcell2.class).addPre(testcell3.class).addPre(testcell4.class));
	classList.put(testcell8.class, new Row().addPre(testcell3.class).addPre(testcell4.class));
	classList.put(testcell9.class, new Row().addPre(testcell5.class).addPre(testcell6.class));
	classList.put(testcell10.class, new Row().addPre(testcell5.class).addPre(testcell6.class).addPre(testcell7.class));
	classList.put(testcell11.class, new Row().addPre(testcell6.class).addPre(testcell7.class).addPre(testcell8.class));
	classList.put(testcell12.class, new Row().addPre(testcell7.class).addPre(testcell8.class));
	classList.put(testcell13.class, new Row().addPre(testcell9.class).addPre(testcell10.class));
	classList.put(testcell14.class, new Row().addPre(testcell9.class).addPre(testcell10.class).addPre(testcell11.class));
	classList.put(testcell15.class, new Row().addPre(testcell10.class).addPre(testcell11.class).addPre(testcell12.class));
	classList.put(testcell16.class, new Row().addPre(testcell11.class).addPre(testcell12.class));

	List<TreeWalker> walkers = new LinkedList<TreeWalker>();
	walkers.add(new testcell1());
	walkers.add(new testcell2());
	walkers.add(new testcell3());
	walkers.add(new testcell4());
	walkers.add(new testcell5());
	walkers.add(new testcell6());
	walkers.add(new testcell7());
	walkers.add(new testcell8());
	walkers.add(new testcell9());
	walkers.add(new testcell10());
	walkers.add(new testcell11());
	walkers.add(new testcell12());
	walkers.add(new testcell13());
	walkers.add(new testcell14());
	walkers.add(new testcell15());
	walkers.add(new testcell16());

	TreeWalkerTable twt = new TreeWalkerTable(walkers, classList);
	List<Cell> cellList = twt.getStartElements();

	twt.cellList.get(testcell6.class).disappear();
	for (Class<? extends TreeWalker> classe : twt.cellList.keySet()) {
	    twt.cellList.get(classe).printPersUndPos();
	}
	twt.cellList.get(testcell7.class).disappear();
	for (Class<? extends TreeWalker> classe : twt.cellList.keySet()) {
	    twt.cellList.get(classe).printPersUndPos();
	}
	twt.cellList.get(testcell10.class).disappear();
	for (Class<? extends TreeWalker> classe : twt.cellList.keySet())
	    twt.cellList.get(classe).printPersUndPos();

	twt.cellList.get(testcell11.class).disappear();

	for (Class<? extends TreeWalker> classe : twt.cellList.keySet()) {
	    twt.cellList.get(classe).printPersUndPos();
	}
	logger.debug("Ende");
    }

    @SuppressWarnings("unused")
    @Test
    public void ТестНа16_2() {
	Map<Class<? extends TreeWalker>, Row> classList = new HashMap<Class<? extends TreeWalker>, Row>();
	classList.put(testcell1.class, new Row());
	classList.put(testcell2.class, new Row());
	classList.put(testcell3.class, new Row());
	classList.put(testcell4.class, new Row());
	classList.put(testcell5.class, new Row().addPre(testcell1.class).addPre(testcell2.class));
	classList.put(testcell6.class, new Row().addPre(testcell1.class).addPre(testcell2.class).addPre(testcell3.class));
	classList.put(testcell7.class, new Row().addPre(testcell2.class).addPre(testcell3.class).addPre(testcell4.class));
	classList.put(testcell8.class, new Row().addPre(testcell3.class).addPre(testcell4.class));
	classList.put(testcell9.class, new Row().addPre(testcell5.class).addPre(testcell6.class));
	classList.put(testcell10.class, new Row().addPre(testcell5.class).addPre(testcell6.class).addPre(testcell7.class));
	classList.put(testcell11.class, new Row().addPre(testcell6.class).addPre(testcell7.class).addPre(testcell8.class));
	classList.put(testcell12.class, new Row().addPre(testcell7.class).addPre(testcell8.class));
	classList.put(testcell13.class, new Row().addPre(testcell9.class).addPre(testcell10.class));
	classList.put(testcell14.class, new Row().addPre(testcell9.class).addPre(testcell10.class).addPre(testcell11.class));
	classList.put(testcell15.class, new Row().addPre(testcell10.class).addPre(testcell11.class).addPre(testcell12.class));
	classList.put(testcell16.class, new Row().addPre(testcell11.class).addPre(testcell12.class));

	List<TreeWalker> walkers = new LinkedList<TreeWalker>();
	walkers.add(new testcell1());
	walkers.add(new testcell2());
	walkers.add(new testcell3());
	walkers.add(new testcell4());
	walkers.add(new testcell5());
	walkers.add(new testcell6());
	walkers.add(new testcell7());
	walkers.add(new testcell8());
	walkers.add(new testcell9());
	walkers.add(new testcell10());
	walkers.add(new testcell11());
	walkers.add(new testcell12());
	walkers.add(new testcell13());
	walkers.add(new testcell14());
	walkers.add(new testcell15());
	walkers.add(new testcell16());

	TreeWalkerTable twt = new TreeWalkerTable(walkers, classList);
	List<Cell> cellList = twt.getStartElements();

	twt.cellList.get(testcell11.class).disappear();
	for (Class<? extends TreeWalker> classe : twt.cellList.keySet())
	    twt.cellList.get(classe).printPersUndPos();
	twt.cellList.get(testcell12.class).disappear();
	for (Class<? extends TreeWalker> classe : twt.cellList.keySet())
	    twt.cellList.get(classe).printPersUndPos();
	twt.cellList.get(testcell15.class).disappear();
	for (Class<? extends TreeWalker> classe : twt.cellList.keySet())
	    twt.cellList.get(classe).printPersUndPos();
	twt.cellList.get(testcell16.class).disappear();

	for (Class<? extends TreeWalker> classe : twt.cellList.keySet())
	    twt.cellList.get(classe).printPersUndPos();

	logger.debug("Ende");
    }

    @SuppressWarnings("unused")
    @Test
    public void ТестНа16_3() {
	Map<Class<? extends TreeWalker>, Row> classList = new HashMap<Class<? extends TreeWalker>, Row>();
	classList.put(testcell1.class, new Row());
	classList.put(testcell2.class, new Row());
	classList.put(testcell3.class, new Row());
	classList.put(testcell4.class, new Row());
	classList.put(testcell5.class, new Row().addPre(testcell1.class).addPre(testcell2.class));
	classList.put(testcell6.class, new Row().addPre(testcell1.class).addPre(testcell2.class).addPre(testcell3.class));
	classList.put(testcell7.class, new Row().addPre(testcell2.class).addPre(testcell3.class).addPre(testcell4.class));
	classList.put(testcell8.class, new Row().addPre(testcell3.class).addPre(testcell4.class));
	classList.put(testcell9.class, new Row().addPre(testcell5.class).addPre(testcell6.class));
	classList.put(testcell10.class, new Row().addPre(testcell5.class).addPre(testcell6.class).addPre(testcell7.class));
	classList.put(testcell11.class, new Row().addPre(testcell6.class).addPre(testcell7.class).addPre(testcell8.class));
	classList.put(testcell12.class, new Row().addPre(testcell7.class).addPre(testcell8.class));
	classList.put(testcell13.class, new Row().addPre(testcell9.class).addPre(testcell10.class));
	classList.put(testcell14.class, new Row().addPre(testcell9.class).addPre(testcell10.class).addPre(testcell11.class));
	classList.put(testcell15.class, new Row().addPre(testcell10.class).addPre(testcell11.class).addPre(testcell12.class));
	classList.put(testcell16.class, new Row().addPre(testcell11.class).addPre(testcell12.class));

	List<TreeWalker> walkers = new LinkedList<TreeWalker>();
	walkers.add(new testcell1());
	walkers.add(new testcell2());
	walkers.add(new testcell3());
	walkers.add(new testcell4());
	walkers.add(new testcell5());
	walkers.add(new testcell6());
	walkers.add(new testcell7());
	walkers.add(new testcell8());
	walkers.add(new testcell9());
	walkers.add(new testcell10());
	walkers.add(new testcell11());
	walkers.add(new testcell12());
	walkers.add(new testcell13());
	walkers.add(new testcell14());
	walkers.add(new testcell15());
	walkers.add(new testcell16());

	TreeWalkerTable twt = new TreeWalkerTable(walkers, classList);
	List<Cell> cellList = twt.getStartElements();

	twt.cellList.get(testcell6.class).disappear();
	for (Class<? extends TreeWalker> classe : twt.cellList.keySet()) {
	    twt.cellList.get(classe).printPersUndPos();
	}
	System.out.println("+++");
	twt.cellList.get(testcell7.class).disappear();
	for (Class<? extends TreeWalker> classe : twt.cellList.keySet()) {
	    twt.cellList.get(classe).printPersUndPos();
	}
	System.out.println("+++");
	twt.cellList.get(testcell8.class).disappear();
	for (Class<? extends TreeWalker> classe : twt.cellList.keySet()) {
	    twt.cellList.get(classe).printPersUndPos();
	}
	System.out.println("+++");
	twt.cellList.get(testcell10.class).disappear();

	for (Class<? extends TreeWalker> classe : twt.cellList.keySet()) {
	    twt.cellList.get(classe).printPersUndPos();
	}
	System.out.println("+++");
	twt.cellList.get(testcell11.class).disappear();

	for (Class<? extends TreeWalker> classe : twt.cellList.keySet()) {
	    twt.cellList.get(classe).printPersUndPos();
	}
	System.out.println("+++");
	twt.cellList.get(testcell12.class).disappear();

	for (Class<? extends TreeWalker> classe : twt.cellList.keySet()) {
	    twt.cellList.get(classe).printPersUndPos();
	}
	System.out.println("+++");
	twt.cellList.get(testcell14.class).disappear();

	for (Class<? extends TreeWalker> classe : twt.cellList.keySet()) {
	    twt.cellList.get(classe).printPersUndPos();
	}
	System.out.println("+++");
	twt.cellList.get(testcell15.class).disappear();

	for (Class<? extends TreeWalker> classe : twt.cellList.keySet()) {
	    twt.cellList.get(classe).printPersUndPos();
	}
	System.out.println("+++");
	twt.cellList.get(testcell16.class).disappear();

	for (Class<? extends TreeWalker> classe : twt.cellList.keySet()) {
	    twt.cellList.get(classe).printPersUndPos();
	}
	logger.debug("Ende");
    }

    @SuppressWarnings("unused")
    @Test
    public void транзитивность() {
	Map<Class<? extends TreeWalker>, Row> classList = new HashMap<Class<? extends TreeWalker>, Row>();
	classList.put(testcell1.class, new Row());
	classList.put(testcell2.class, new Row().Checker());
	classList.put(testcell3.class, new Row().addPre(testcell1.class).addPre(testcell2.class));
	classList.put(testcell4.class, new Row().addPre(testcell1.class).addPre(testcell2.class).Checker());
	classList.put(testcell5.class, new Row().addPre(testcell3.class).addPre(testcell4.class));
	classList.put(testcell6.class, new Row().addPre(testcell3.class).addPre(testcell4.class).Checker());
	classList.put(testcell7.class, new Row().addPre(testcell5.class).addPre(testcell6.class));
	classList.put(testcell8.class, new Row().addPre(testcell5.class).addPre(testcell6.class).Checker());

	List<TreeWalker> walkers = new LinkedList<TreeWalker>();
	walkers.add(new testcell1());
	walkers.add(new testcell2());
	walkers.add(new testcell3());
	walkers.add(new testcell4());
	walkers.add(new testcell5());
	walkers.add(new testcell6());
	walkers.add(new testcell7());
	walkers.add(new testcell8());

	TreeWalkerTable twt = new TreeWalkerTable(walkers, classList);

	System.out.println("о");
    }
}
