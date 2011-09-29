package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;

public class Not extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		// XXX return null после фикса
		return getSuccsessor().getConvertedSubtree();
	}

}
