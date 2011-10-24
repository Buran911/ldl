package parse.parsetree.nodes;

import generation.languageconstants.Logical;
import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.SetOpAST;

public class SetOp extends Node {
	private Logical logical;

	public SetOp(Logical logical) {
		this.logical = logical;
	}

	public Logical getLogical() {
		return logical;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		return new SetOpAST(getLogical());
	}
}
