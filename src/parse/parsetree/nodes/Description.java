package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.DescriptionAST;
import parse.syntaxtree.nodes.IdentifierAST;
import parse.syntaxtree.nodes.TypeAST;

public class Description extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		DescriptionAST description = new DescriptionAST();
		
		for(Node node : getSuccsessors()){
			if (node instanceof Identifier){
				description.setIdentifier((IdentifierAST) node.getConvertedSubtree());
			}
			
			if (node instanceof Type){
				description.setType((TypeAST) node.getConvertedSubtree());
			}
		}
		
		return description;
	}

}
