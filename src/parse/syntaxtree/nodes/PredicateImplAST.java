package parse.syntaxtree.nodes;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class PredicateImplAST extends NodeAST {
	private PathNameAST pathName;
	private FunctionalPartAST funcPart;
	private FormalParamsAST formalParams;
	private List<ConstraintAST> constraints;
	
	{
		constraints = new LinkedList<ConstraintAST>();
	}
	
	public void setPathName(PathNameAST pathName) {
		this.pathName = pathName;
		addSuccessor(pathName);
	}

	public void setFuncPart(FunctionalPartAST funcPart) {
		this.funcPart = funcPart;
		addSuccessor(funcPart);
	}

	public void setFormalParams(FormalParamsAST formalParams) {
		this.formalParams = formalParams;
		addSuccessor(formalParams);
	}

	public void addConstraint(ConstraintAST constraint) {
		constraints.add(constraint);
		addSuccessor(constraint);
	}

	public PathNameAST getPathName() {
		return pathName;
	}

	public FunctionalPartAST getFuncPart() {
		return funcPart;
	}

	public FormalParamsAST getFormalParams() {
		return formalParams;
	}

	public List<ConstraintAST> getConstraints() {
		return constraints;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

}
