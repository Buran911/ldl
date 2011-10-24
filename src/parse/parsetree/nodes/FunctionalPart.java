package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.FunctionalPartAST;
import parse.syntaxtree.nodes.NumberAST;
import parse.syntaxtree.nodes.StringAST;

public class FunctionalPart extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		FunctionalPartAST funcPart = new FunctionalPartAST();
		
		if(getSuccsessors().size() == 0){
			return funcPart;
		}
		
		for(Node node : getSuccsessors()){
			if( node instanceof LString ){
				funcPart.setGroup((StringAST) node.getConvertedSubtree());
			}
			
			if(node instanceof Number){
				funcPart.setPriority((NumberAST) node.getConvertedSubtree());
			}
		}
		
		return funcPart;
	}

}
