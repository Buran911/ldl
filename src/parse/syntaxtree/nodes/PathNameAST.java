package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.NodeAST;

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

	public SimpleNameAST getContextName() {
		return contextName;
	}

	public SimpleNameAST getPredicateName() {
		return predicateName;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

}
