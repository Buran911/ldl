package parse.parsetree.nodes;

import java.util.LinkedList;
import java.util.List;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.ConstraintAST;
import parse.syntaxtree.nodes.FormalParamsAST;
import parse.syntaxtree.nodes.FunctionalPartAST;
import parse.syntaxtree.nodes.PathNameAST;
import parse.syntaxtree.nodes.PredicateImplAST;

public class PredicateImpl extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		PredicateImplAST impl = new PredicateImplAST();
		
		for(Node node : getSuccsessors()){
			if(node instanceof PathName){
				impl.setPathName((PathNameAST)node.getConvertedSubtree());
			}
			
			if(node instanceof FunctionalPart){
				impl.setFuncPart((FunctionalPartAST)node.getConvertedSubtree());
			}
			
			if(node instanceof FormalParams){
				impl.setFormalParams((FormalParamsAST)node.getConvertedSubtree());
			}

			if(node instanceof Constraints){
				List<Node> constraints = new LinkedList<Node>();
				node.makeLinearList(constraints);
				
				for(Node constraint : constraints){
					impl.addConstraint((ConstraintAST)constraint.getConvertedSubtree());
				}
			}
		}
		
		return impl;
	}

}
