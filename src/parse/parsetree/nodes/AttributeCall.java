package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.AttributeCallAST;
import parse.syntaxtree.nodes.IdentifierAST;
import parse.syntaxtree.nodes.VariableAST;

public class AttributeCall extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		AttributeCallAST attrCall = new AttributeCallAST();
		
		for(Node node : getSuccsessors()){
			if(node instanceof AttributeCall){
				attrCall.setAttrCall(  (AttributeCallAST) node.getConvertedSubtree());
			}
			
			if(node instanceof Variable){
				attrCall.setVariable( (VariableAST) node.getConvertedSubtree());
			}
			
			if(node instanceof Identifier){
				attrCall.setIdentifier( (IdentifierAST) node.getConvertedSubtree());
			}
		}
		
		return attrCall;
	}

}
