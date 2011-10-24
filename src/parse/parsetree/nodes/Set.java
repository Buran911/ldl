package parse.parsetree.nodes;

import java.util.LinkedList;
import java.util.List;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.LiteralAST;
import parse.syntaxtree.nodes.SetAST;

public class Set extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		SetAST set = new SetAST();
		List<Node> elements = new LinkedList<Node>();
		List<Node> elementChilds = new LinkedList<Node>();
		
		getSuccsessor().makeLinearList(elements);
		for(Node element : elements){
			elementChilds.add(element.getSuccsessor());
		}
		
		for(Node child : elementChilds){
			set.addElement((LiteralAST) child.getConvertedSubtree());
		}
		
		return set;
	}

}
