package parse.parsetree.nodes;

import parse.parsetree.Node;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.nodes.ParametresAST;
import parse.syntaxtree.nodes.PathNameAST;
import parse.syntaxtree.nodes.SimpleNameAST;

public class PathName extends Node {

	@Override
	public NodeAST getConvertedSubtree() {
		PathNameAST pathName = new PathNameAST();
		
		pathName.setContextName((SimpleNameAST) getSuccsessor(0).getConvertedSubtree());
		pathName.setPredicateName((SimpleNameAST) getSuccsessor(1).getConvertedSubtree());
		
		return pathName;
	}

}
