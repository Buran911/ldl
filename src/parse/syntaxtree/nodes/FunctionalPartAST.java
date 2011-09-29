package parse.syntaxtree.nodes;

import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class FunctionalPartAST extends NodeAST {
	private StringAST group;
	private NumberAST priority;
	
	public void setGroup(StringAST group) {
		this.group = group;
		addSuccessor(group);
	}

	public void setPriority(NumberAST priority) {
		this.priority = priority;
		addSuccessor(priority);
	}

	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub

	}

}
