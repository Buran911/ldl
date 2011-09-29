package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.IdentifierAST;
import parse.syntaxtree.nodes.LiteralAST;
import parse.syntaxtree.nodes.SetAST;
import parse.syntaxtree.nodes.srcExprAST;

public class srcExpr extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		srcExprAST srcExp = new srcExprAST();
		boolean firstId = true;
		
		for(Node node : getSuccsessors()){
			if(node instanceof Identifier){
				if( firstId ){
					srcExp.setFirstId((IdentifierAST) node.getConvertedSubtree());
					firstId = false;
				}
				else{
					srcExp.setSecondId((IdentifierAST) node.getConvertedSubtree());
				}
			}
			
			if(node instanceof Set){
				srcExp.setSet((SetAST) node.getConvertedSubtree());
			}
			
			if((node instanceof Number) 
					|| (node instanceof LString)
					|| (node instanceof Bool)){
				srcExp.setLiteral( (LiteralAST) node.getConvertedSubtree());
			}
			
		}
		
		return srcExp;
	}

}
