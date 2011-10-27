package parse.parsetree.nodes;

import parse.parsetree.PositionableNode;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.BooleanAST;

public class Bool extends PositionableNode {
	private boolean bool;

	public Bool(boolean bool, Integer lineNo, Integer columnNo) {
		super(lineNo, columnNo);
		this.bool = bool;
	}

	public boolean getBool() {
		return bool;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		return new BooleanAST(getBool(), getLineNo(), getColumnNo());
	}
}
