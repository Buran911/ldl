package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;

public class ConditionAST extends ConstraintAST {
    private BinaryAST condition;
    private List<ConstraintAST> constraints;
    private List<ConstraintAST> eqClasseses;

    {
	constraints = new LinkedList<ConstraintAST>();
	eqClasseses = new LinkedList<ConstraintAST>();
    }

    public void setCondition(BinaryAST condition) {
	this.condition = condition;
    }

    public void addConstraint(ConstraintAST constraint) {
	constraints.add(constraint);
	addSuccessor(constraint);
    }

    public void addEqClasses(ConstraintAST eqClasses) {
	eqClasseses.add(eqClasses);
	addSuccessor(eqClasses);
    }

    public BinaryAST getCondition() {
	return condition;
    }

    public List<ConstraintAST> getConstraints() {
	return constraints;
    }

    public List<ConstraintAST> getEqClasseses() {
	return eqClasseses;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    public ConditionAST clone() {
	ConditionAST copy = new ConditionAST();
	return copy;
    }

}
