package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.IdentifierAST;
import parse.syntaxtree.nodes.VariableAST;

public class Variable extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		VariableAST var = new VariableAST();
		
		var.setIdentifier( (IdentifierAST) getSuccsessor().getConvertedSubtree());
		
		return var;
	}

}
