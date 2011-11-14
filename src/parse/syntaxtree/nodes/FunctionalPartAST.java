package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.NodeAST;

public class FunctionalPartAST extends NodeAST implements Cloneable{
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
    public FunctionalPartAST clone() {
	FunctionalPartAST copy = new FunctionalPartAST();
	copy.setGroup(this.group.clone());
	copy.setPriority(this.priority.clone());
	
	return copy;
    }

}
