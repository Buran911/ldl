package parse.syntaxtree.nodes;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class ContextAST extends NodeAST {
	private SimpleNameAST contextName;
	private List<DescriptionAST> descriptions;
	private List<SourceAST> sources;
	private List<ConstraintAST> constraints;
	private List<ConstraintAST> eqClasseses;
	
	{
		descriptions = new LinkedList<DescriptionAST>();
		sources = new LinkedList<SourceAST>();
		constraints = new LinkedList<ConstraintAST>();
		eqClasseses = new LinkedList<ConstraintAST>();
	}


	public void setContextName(SimpleNameAST contextName) {
		this.contextName = contextName;
		addSuccessor(contextName);
	}


	public void addDescription(DescriptionAST description) {
		descriptions.add(description);
		addSuccessor(description);
	}


	public void addSource(SourceAST source) {
		sources.add(source);
		addSuccessor(source);
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
