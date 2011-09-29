package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.RelationAST;

public class Relation extends Node {
	private parse.parsetree.Ratio relation;

	public Relation(parse.parsetree.Ratio relation) {
		this.relation = relation;
	}

	public parse.parsetree.Ratio getRelation() {
		return relation;
	}

	@Override
	public NodeAST getConvertedSubtree() {
		return new RelationAST(getRelation());
	}
}
