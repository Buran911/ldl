package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.RelationAST;

public class Relation extends Node {
	private generation.languageconstants.Ratio relation;

	public Relation(generation.languageconstants.Ratio relation) {
		this.relation = relation;
	}

	public generation.languageconstants.Ratio getRelation() {
		return relation;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		return new RelationAST(getRelation());
	}
}
