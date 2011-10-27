package parse.parsetree.nodes;

import generation.languageconstants.Logical;
import parse.parsetree.PositionableNode;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.SetOpAST;

public class SetOp extends PositionableNode {
	private Logical logical;

	public SetOp(Logical logical, Integer lineNo, Integer columnNo) {
		super(lineNo, columnNo);
		this.logical = logical;
	}

	public Logical getLogical() {
		return logical;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		return new SetOpAST(getLogical(), getLineNo(), getColumnNo());
	}
}
