package parse.syntaxtree.nodes;

import parse.syntaxtree.NodeAST;
import generation.walkers.TreeWalker;

public abstract class BinaryAST extends ConstraintAST  implements Cloneable {
	Boolean not;
	
	{
		not = false;
	}
	public abstract BinaryAST clone();
}
