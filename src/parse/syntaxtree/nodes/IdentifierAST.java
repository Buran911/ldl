package parse.syntaxtree.nodes;

import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class IdentifierAST extends NodeAST {
	private String id;
	
	public IdentifierAST(String id) {
		this.id = id;
	}

	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub
		
	}

}
