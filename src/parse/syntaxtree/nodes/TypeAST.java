package parse.syntaxtree.nodes;

import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class TypeAST extends NodeAST {
	private String type;
	
	public TypeAST(String type) {
		this.type = type;
	}
	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

}
