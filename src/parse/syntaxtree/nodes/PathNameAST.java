package parse.syntaxtree.nodes;

import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class PathNameAST extends NodeAST {
	private SimpleNameAST contextName;
	private SimpleNameAST predicateName;
	
	public void setContextName(SimpleNameAST contextName) {
		this.contextName = contextName;
		addSuccessor(contextName);
	}

	public void setPredicateName(SimpleNameAST predicateName) {
		this.predicateName = predicateName;
		addSuccessor(predicateName);
	}

	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub

	}

}
