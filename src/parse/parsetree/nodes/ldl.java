package parse.parsetree.nodes;
/**
 * Корень дерева разбора.
 * */
import java.util.LinkedList;
import java.util.List;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.ContextAST;
import parse.syntaxtree.nodes.PredicateImplAST;
import parse.syntaxtree.nodes.ldlAST;

public class ldl extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		ldlAST _ldl = new ldlAST();
		List<Node> ldlExpressions = new LinkedList<Node>();
		List<Node> exprChilds = new LinkedList<Node>();
		
		this.getSuccsessors().get(0).makeLinearList(ldlExpressions);
		for (Node node : ldlExpressions){
			exprChilds.add(node.getSuccsessor());
		}
		
		for (Node node : exprChilds) {
			if(node instanceof Context){
				_ldl.addContext((ContextAST) node.getConvertedSubtree());
			}
			
			if(node instanceof PredicateImpl){
				_ldl.addPredicateImpl((PredicateImplAST) node.getConvertedSubtree());
			}
		}
		
		return _ldl;
	}

}
