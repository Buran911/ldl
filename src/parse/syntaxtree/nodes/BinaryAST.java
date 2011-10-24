package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

public abstract class BinaryAST extends ConstraintAST {
	Boolean not;
	
	{
		not = false;
	}
}
