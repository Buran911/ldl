package parse.parsetree.nodes;

import parse.parsetree.PositionableNode;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.IdentifierAST;

public class Identifier extends PositionableNode {
	private String identifier;

	public Identifier(String identifier, Integer lineNo, Integer columnNo) {
		super(lineNo, columnNo);
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		return new IdentifierAST(getIdentifier(), getLineNo(), getColumnNo());
	}
}
