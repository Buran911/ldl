package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.IdentifierAST;
import parse.syntaxtree.nodes.OperationCallAST;

public class OperationCall extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		OperationCallAST operCall = new OperationCallAST();
		
		for(Node node : getSuccsessors()){
			if(node instanceof Identifier){
				operCall.setIdentifier( (IdentifierAST) node.getConvertedSubtree());
			}
			
			if(node instanceof Parametres){
				// XXX Когда-о здесь будет жить код 
			}
		}
		
		return operCall;
	}

}
