package parse.parsetree.nodes;

import parse.parsetree.PositionableNode;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.RelationAST;

public class Relation extends PositionableNode {
	private generation.languageconstants.Ratio relation;

	public Relation(generation.languageconstants.Ratio relation, Integer lineNo, Integer columnNo) {
		super(lineNo, columnNo);
		this.relation = relation;
	}

	public generation.languageconstants.Ratio getRelation() {
		return relation;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		return new RelationAST(getRelation(), getLineNo(), getColumnNo());
	}
}
