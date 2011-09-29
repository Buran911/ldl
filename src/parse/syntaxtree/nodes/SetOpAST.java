package parse.syntaxtree.nodes;

import parse.parsetree.Logical;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class SetOpAST extends NodeAST {
	private Logical operation;
	
	public SetOpAST(Logical operation) {
		this.operation = operation;
	}


	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub

	}

}
