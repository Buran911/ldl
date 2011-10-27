package parse.parsetree.nodes;

import parse.parsetree.PositionableNode;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.StringAST;

public class LString extends PositionableNode {
	private String string;

	public LString(String string, Integer lineNo, Integer columnNo) {
		super( lineNo, columnNo );
		this.string = string;
	}

	public String getString() {
		return string;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		return new StringAST(getString(), getLineNo(), getColumnNo());
	}
}
