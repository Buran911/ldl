package testcases.unit.semantic;

import generation.walkers.TreeWalker;
import generation.walkers.strategys.BottomUpWalkingStrategy;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.FunctionalImplementedChecker;
import generation.walkers.walkers.IdNotDefinedChecker;
import generation.walkers.walkers.IdRedefinedChecker;
import generation.walkers.walkers.IdTableFiller;
import generation.walkers.walkers.IdTableMaker;
import generation.walkers.walkers.PositionEstimater;
import generation.walkers.walkers.TypeMismatchChecker;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import parse.errhandler.TreeWalkerTable;

public class TreeWalkerTableTest {

    @Test
    public void func() {
	LinkedList<TreeWalker> walkers = new LinkedList<TreeWalker>();

	walkers.add(new FunctionalImplementedChecker(new BottomUpWalkingStrategy(), null));
	walkers.add(new IdTableMaker(new IdParsigStrategy(), null));
	walkers.add(new IdNotDefinedChecker(new IdParsigStrategy(), null, null));
	walkers.add(new TypeMismatchChecker(new IdParsigStrategy(), null));
//	walkers.add(new IdConvertor(new IdParsigStrategy(), null));
	walkers.add(new IdRedefinedChecker(new IdParsigStrategy(), null));
	walkers.add(new PositionEstimater(new IdParsigStrategy()));
	walkers.add(new IdTableFiller(new IdParsigStrategy(), null));
	
	@SuppressWarnings("unused")
	TreeWalkerTable twt = TreeWalkerTable.getInstance(walkers);

	System.out.println("Ende");
	
    }
}