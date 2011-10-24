package parse.parsetree.nodes;

import java.util.LinkedList;
import java.util.List;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.BinaryAST;
import parse.syntaxtree.nodes.ConditionAST;
import parse.syntaxtree.nodes.ConstraintAST;

public class Condition extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		ConditionAST condition = new ConditionAST();
		
		for(Node node : getSuccsessors()){
			if(node instanceof Binary){
				condition.setCondition( (BinaryAST) node.getConvertedSubtree());
			}
			
			if(node instanceof IfBlocks){
				List<Node> blocks = new LinkedList<Node>();
				List<Node> blockChilds = new LinkedList<Node>();
				
				node.makeLinearList(blocks);
				for(Node block : blocks){
					blockChilds.add(block.getSuccsessor());
				}
				
				for(Node child : blockChilds){
					if(child instanceof Constraint){
						condition.addConstraint((ConstraintAST) child.getConvertedSubtree());
					}
					
					if(child instanceof EqClasses){
						condition.addEqClasses((ConstraintAST) child.getConvertedSubtree());
					}
				}
			}
		}
		
		return condition;
	}

}
