package parse.syntaxtree.nodes;

import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class SimpleNameAST extends NodeAST {
	private String name;
	
	public SimpleNameAST(String name) {
		this.name = name;
	}

	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub

	}

}
