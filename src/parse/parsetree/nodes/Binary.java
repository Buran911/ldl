package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.BinaryAST;
import parse.syntaxtree.nodes.BinaryExpAST;
import parse.syntaxtree.nodes.BinaryOpAST;
import parse.syntaxtree.nodes.SetOpAST;

public class Binary extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		Node succ = getSuccsessor();
		
		if(succ instanceof BinaryExp){
			return getSuccsessor().getConvertedSubtree();
		}
		
		// FIXME Not учесть
		if(succ instanceof Not){
			return getSuccsessor().getConvertedSubtree();
		}
		
		if(succ instanceof Binary){
			BinaryOpAST binOp = new BinaryOpAST();
			boolean firstBin = true;
			
			for( Node node : getSuccsessors()){
				if(node instanceof Binary){
					if(firstBin){
						binOp.setBinary((BinaryAST) node.getConvertedSubtree());
						firstBin = false;
					}
					else{
						binOp.setBinaryExp((BinaryExpAST) node.getConvertedSubtree());
					}
				}
				
				if(node instanceof SetOp){
					binOp.setSetOp((SetOpAST) node.getConvertedSubtree());
				}
				
				if(node instanceof BinaryExp){
					binOp.setBinaryExp((BinaryExpAST) node.getConvertedSubtree());
				}
			}
			
			return binOp;
		}
		
		// Ошибка
		throw new RuntimeException("Не могу создать BinaryAST");
	}

}
