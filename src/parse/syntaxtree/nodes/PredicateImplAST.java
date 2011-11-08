package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;
import parse.util.Positionable;

public class PredicateImplAST extends NodeAST implements Positionable {
	private PathNameAST pathName;
	private FunctionalPartAST funcPart;
	private FormalParamsAST formalParams;
	private List<ConstraintAST> constraints;
	private Integer lineNo;
	private Integer columnNo;
	
	
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

	public Integer getLineNo() {
		return lineNo;
	}

	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}

	public Integer getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(Integer columnNo) {
		this.columnNo = columnNo;
	}

}
