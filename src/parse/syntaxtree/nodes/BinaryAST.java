package parse.syntaxtree.nodes;

import parse.syntaxtree.TreeWalker;

public abstract class BinaryAST extends ConstraintAST {
	Boolean not;
	
	{
		not = false;
	}
}
