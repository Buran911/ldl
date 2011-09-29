package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.AttributeCallAST;
import parse.syntaxtree.nodes.OperationCallAST;
import parse.syntaxtree.nodes.PredicateAST;
import parse.syntaxtree.nodes.VariableAST;

public class Predicate extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		PredicateAST predicate = new PredicateAST();
		
		for(Node node : getSuccsessors()){
			if(node instanceof OperationCall){
				predicate.setOprCall((OperationCallAST) node.getConvertedSubtree());
			}
			
			if(node instanceof Variable){
				predicate.setVariable((VariableAST) node.getConvertedSubtree());
			}
			
			if(node instanceof AttributeCall){
				predicate.setAttrCall((AttributeCallAST) node.getConvertedSubtree());
			}
		}
		return predicate;
	}

}
