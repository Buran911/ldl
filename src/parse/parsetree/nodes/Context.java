package parse.parsetree.nodes;

import java.util.LinkedList;
import java.util.List;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.ConstraintAST;
import parse.syntaxtree.nodes.ContextAST;
import parse.syntaxtree.nodes.DescriptionAST;
import parse.syntaxtree.nodes.EqClassAST;
import parse.syntaxtree.nodes.SimpleNameAST;
import parse.syntaxtree.nodes.SourceAST;
import parse.util.Source;

public class Context extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		ContextAST context = new ContextAST();
		
		for(Node node : getSuccsessors()){
			if(node instanceof SimpleName){
				context.setContextName((SimpleNameAST) node.getConvertedSubtree());
			}
			
			if(node instanceof Blocks){
				List<Node> blocks = new LinkedList<Node>();
				List<Node> blockChilds = new LinkedList<Node>();
				
				node.makeLinearList(blocks);
				
				for(Node block : blocks){
					blockChilds.add(block.getSuccsessor());
				}
				
				for(Node child : blockChilds){
					if(child instanceof Description){
						context.addDescription((DescriptionAST) child.getConvertedSubtree());
					}
					
					if(child instanceof Src){
						context.addSource((SourceAST) child.getConvertedSubtree());
					}
					
					if(child instanceof Constraint){
						context.addConstraint((ConstraintAST) child.getConvertedSubtree());
					}
					
					if(child instanceof EqClasses){
						context.addEqClass((EqClassAST) child.getConvertedSubtree());
//						List<Node> constraints = new LinkedList<Node>();
//						
//						child.getSuccsessor().makeLinearList(constraints);
//						for(Node constraint : constraints){
//							context.addEqClasses((ConstraintAST) constraint.getConvertedSubtree());
//						}
					}
				}
				
			}
		}
		
		return context;
	}

}
