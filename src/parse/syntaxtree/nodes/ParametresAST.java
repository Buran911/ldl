package parse.syntaxtree.nodes;

import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class ParametresAST extends NodeAST {

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

}
