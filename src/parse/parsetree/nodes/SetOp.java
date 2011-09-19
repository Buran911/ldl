package parse.parsetree.nodes;

import parse.parsetree.Logical;
import parse.parsetree.Node;

public class SetOp extends Node {
	private Logical logical;

	public SetOp(Logical logical) {
		this.logical = logical;
	}

	public Logical getLogical() {
		return logical;
	}
}
