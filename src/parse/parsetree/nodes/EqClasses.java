package parse.parsetree.nodes;

import java.util.LinkedList;
import java.util.List;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.ConstraintAST;
import parse.syntaxtree.nodes.EqClassAST;

public class EqClasses extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		EqClassAST eqClass = new EqClassAST();
		
		List<Node> constraints = new LinkedList<Node>();
		
		this.getSuccsessor().makeLinearList(constraints);
		for(Node constraint : constraints){
			eqClass.addConstraint((ConstraintAST) constraint.getConvertedSubtree());
		}
		
		return eqClass;
	}

}
