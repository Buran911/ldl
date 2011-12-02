package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;

public class EqClassAST extends NodeAST {
    private List<ConstraintAST> constraints;

    {
	constraints = new LinkedList<ConstraintAST>();
    }

    public ConstraintAST getConstraint(int index) {
	return constraints.get(index);
    }

    public List<ConstraintAST> getConstraints() {
	return constraints;
    }

    public void addConstraint(ConstraintAST constraint) {
	constraints.add(constraint);
	addSuccessor(constraint);
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);
    }
    
    @Override
    public EqClassAST clone() {
	EqClassAST copy = new EqClassAST();

	for (ConstraintAST constraint : constraints) {
	    copy.addConstraint(constraint.clone());
	}
	return copy;
    }

}
