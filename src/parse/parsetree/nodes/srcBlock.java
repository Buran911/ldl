package parse.parsetree.nodes;

import java.util.LinkedList;
import java.util.List;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.IdentifierAST;
import parse.syntaxtree.nodes.srcBlockAST;
import parse.syntaxtree.nodes.srcExprAST;

public class srcBlock extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		srcBlockAST block = new srcBlockAST();
		
		for(Node node : getSuccsessors()){
			if(node instanceof Identifier){
				block.setIdentifier((IdentifierAST) node.getConvertedSubtree());
			}
			
			if(node instanceof srcExprs){
				List<Node> srcExprss = new LinkedList<Node>();
				
				node.makeLinearList(srcExprss);
				for(Node expr : srcExprss){
					block.addSrcExpr((srcExprAST) expr.getConvertedSubtree());
				}
				
			}
		}
		
		return block;
	}

}
