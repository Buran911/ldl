package parse.parsetree.nodes;

import parse.parsetree.PositionableNode;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.SimpleNameAST;

public class SimpleName extends PositionableNode {
	private String name;

	public SimpleName(String name, Integer lineNo, Integer columnNo) {
		super(lineNo, columnNo);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		SimpleNameAST simpleName = new SimpleNameAST(getName(), getLineNo(), getColumnNo());
		
		return simpleName;
	}
}
