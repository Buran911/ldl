package parse.syntaxtree.nodes;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

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
	
	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub

	}

}
