package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

public class PredicateAST extends BinaryExpAST {
	private AttributeCallAST attrCall;
	private VariableAST variable;
	private OperationCallAST oprCall;
	private PredicateImplAST impl;
	
	public void setAttrCall(AttributeCallAST attrCall) {
		this.attrCall = attrCall;
		addSuccessor(attrCall);
	}

	public void setVariable(VariableAST variable) {
		this.variable = variable;
		addSuccessor(variable);
	}

	public void setOprCall(OperationCallAST oprCall) {
		this.oprCall = oprCall;
		addSuccessor(oprCall);
	}

	public AttributeCallAST getAttrCall() {
		return attrCall;
	}

	public VariableAST getVariable() {
		return variable;
	}

	public OperationCallAST getOprCall() {
		return oprCall;
	}

	public PredicateImplAST getImpl() {
		return impl;
	}

	public void setImpl(PredicateImplAST impl) {
		this.impl = impl;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

}
