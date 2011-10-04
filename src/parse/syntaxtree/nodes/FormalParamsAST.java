package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.NodeAST;

public class FormalParamsAST extends NodeAST {

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

}
