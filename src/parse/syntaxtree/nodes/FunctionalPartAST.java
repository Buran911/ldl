package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.NodeAST;

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

    public StringAST getGroup() {
	return group;
    }

    public NumberAST getPriority() {
	return priority;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    @Override
    public Object clone() {
	FunctionalPartAST copy = new FunctionalPartAST();
	if ((group != null) && (priority != null)) {
	    copy.setGroup((StringAST) group.clone());
	    copy.setPriority((NumberAST) priority.clone());
	}
	return copy;
    }

}
