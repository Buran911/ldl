package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

public class ConditionAST extends ConstraintAST {
    private BinaryAST condition;
    private List<ConstraintAST> constraints;
    private List<ConstraintAST> eqClassesList;

    {
	constraints = new LinkedList<ConstraintAST>();
	eqClassesList = new LinkedList<ConstraintAST>();
    }

    public void setCondition(BinaryAST condition) {
	this.condition = condition;
    }

    public void addConstraint(ConstraintAST constraint) {
	constraints.add(constraint);
	addSuccessor(constraint);
    }

    public void addEqClasses(ConstraintAST eqClassesParam) {
	eqClassesList.add(eqClassesParam);
	addSuccessor(eqClassesParam);
    }

    public BinaryAST getCondition() {
	return condition;
    }

    public List<ConstraintAST> getConstraints() {
	return constraints;
    }

    public List<ConstraintAST> getEqClasseses() {
	return eqClassesList;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    @Override
    public ConditionAST clone() {
	ConditionAST copy = new ConditionAST();
	copy.condition = condition.clone();

	for (ConstraintAST constraint : constraints) {
	    copy.addConstraint(constraint.clone());
	}
	for (ConstraintAST eqClass : eqClassesList) {
	    copy.addEqClasses(eqClass.clone());
	}
	return copy;
    }

}
