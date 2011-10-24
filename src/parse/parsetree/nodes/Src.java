package parse.parsetree.nodes;

import java.util.LinkedList;
import java.util.List;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.SourceAST;
import parse.syntaxtree.nodes.srcBlockAST;

public class Src extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		SourceAST src = new SourceAST();
		List<Node> srcBlocks = new LinkedList<Node>();
		
		getSuccsessor().makeLinearList(srcBlocks);
		
		for(Node block : srcBlocks){
			src.addSrcBlock((srcBlockAST) block.getConvertedSubtree());
		}
		
		
		return src;
	}

}
