package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.BinaryExpressionAST;
import parse.syntaxtree.nodes.ExpressionAST;
import parse.syntaxtree.nodes.RelationAST;

public class BinaryExp extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		if(getSuccsessor() instanceof Predicate){
			return getSuccsessor().getConvertedSubtree();
		}
		else{
			BinaryExpressionAST binExp = new BinaryExpressionAST();
			boolean firstExp = true;
			
			for(Node node : getSuccsessors()){
				if(node instanceof Expression){
					if(firstExp){
						binExp.setFirstExpression( (ExpressionAST) node.getConvertedSubtree());
						firstExp = false;
					}
					else{
						binExp.setSecondExpression( (ExpressionAST) node.getConvertedSubtree());
					}
				}
				
				if(node instanceof Relation){
					binExp.setRelation( (RelationAST) node.getConvertedSubtree());
				}
			}
			
			return binExp;
		}
		
	}

}
