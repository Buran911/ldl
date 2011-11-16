package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;

public class ConditionAST extends ConstraintAST {
    private BinaryAST condition;
    private List<ConstraintAST> constraints;
    // XXX eqClasseses -> eqClasses , разобраться с названиями
    private List<ConstraintAST> eqClasses;

    {
	constraints = new LinkedList<ConstraintAST>();
	eqClasses = new LinkedList<ConstraintAST>();
    }

    public void setCondition(BinaryAST condition) {
	this.condition = condition;
    }

    public void addConstraint(ConstraintAST constraint) {
	constraints.add(constraint);
	addSuccessor(constraint);
    }

    public void addEqClasses(ConstraintAST eqClassesParam) {
	eqClasses.add(eqClassesParam);
	addSuccessor(eqClassesParam);
    }

    public BinaryAST getCondition() {
	return condition;
    }

    public List<ConstraintAST> getConstraints() {
	return constraints;
    }

    public List<ConstraintAST> getEqClasseses() {
	return eqClasses;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    public ConditionAST clone() {
	ConditionAST copy = new ConditionAST();
	copy.condition = condition.clone();

	for (ConstraintAST constraint : constraints) {
	    copy.addConstraint(constraint.clone());
	}
	for (ConstraintAST eqClass : eqClasses) {
	    copy.addEqClasses(eqClass.clone());
	}
	return copy;
    }

}
