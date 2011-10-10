package generation.templateengine;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.nodes.ConstraintAST;

public class EqualityClass {
	private List<ConstraintAST> constraints;
	
	{
		constraints = new LinkedList<ConstraintAST>();
	}
	
	public void addConstraint(ConstraintAST constraint){
		constraints.add(constraint);
	}

	public void addConstraints(List<ConstraintAST> constraints){
		this.constraints.addAll(constraints);
	}
	
	public List<ConstraintAST> getConstraints() {
		return constraints;
	}
}
