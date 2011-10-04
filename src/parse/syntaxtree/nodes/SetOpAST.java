package parse.syntaxtree.nodes;

import generation.languageconstants.Logical;
import generation.walkers.TreeWalker;
import parse.syntaxtree.Data;
import parse.syntaxtree.NodeAST;

public class SetOpAST extends NodeAST implements Data {
	private Logical operation;
	
	public SetOpAST(Logical operation) {
		this.operation = operation;
	}


	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}


	@Override
	public String getData() {
		return operation.value();
	}

}
