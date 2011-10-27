package parse.parsetree.nodes;

import parse.parsetree.PositionableNode;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.TypeAST;

public class Type extends PositionableNode {
	private String type;

	public Type(String type, Integer lineNo, Integer columnNo) {
		super(lineNo, columnNo);
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		return new TypeAST(getType(), getLineNo(), getColumnNo());
	}
	
}
