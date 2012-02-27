package parse.parsetree.nodes;

import java.util.LinkedList;
import java.util.List;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.IdentifierAST;
import parse.syntaxtree.nodes.LiteralAST;
import parse.syntaxtree.nodes.SetAST;
import parse.syntaxtree.nodes.srcExprAST;

public class srcExpr extends Node {

    @Override
    public NodeAST getConvertedSubtree() {
	srcExprAST srcExp = new srcExprAST();

	for (Node node : getSuccsessors()) {
	    if (node instanceof Identifier) {

		srcExp.setFirstId((IdentifierAST) node.getConvertedSubtree());
	    }

	    if (node instanceof Identifiers) {
		List<Node> nodes = new LinkedList<Node>();
		node.makeLinearList(nodes);
		for (Node tempNode : nodes){
		    srcExp.addSecondId((IdentifierAST) tempNode.getConvertedSubtree());
		}
	    }

	    if (node instanceof Set) {
		srcExp.setSet((SetAST) node.getConvertedSubtree());
	    }

	    if ((node instanceof Number) || (node instanceof LString) || (node instanceof Bool)) {
		srcExp.setLiteral((LiteralAST) node.getConvertedSubtree());
	    }

	}

	return srcExp;
    }

}
