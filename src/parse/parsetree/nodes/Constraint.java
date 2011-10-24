package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;

public class Constraint extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		// �������, ����������� �����
		return getSuccsessor().getConvertedSubtree();
	}

}
