package parse.parsetree.nodes;

import parse.parsetree.PositionableNode;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.NumberAST;

public class Number extends PositionableNode {
	private Double number;

	public Number(Double number, Integer lineNo, Integer columnNo) {
		super(lineNo, columnNo);
		this.number = number;
	}

	public Double getNumber() {
		return number;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		return new NumberAST(getNumber(), getLineNo(), getColumnNo());
	}
	
}
