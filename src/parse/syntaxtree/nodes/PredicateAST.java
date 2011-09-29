package parse.syntaxtree.nodes;

import parse.syntaxtree.TreeWalker;

public class PredicateAST extends BinaryExpAST {
	private AttributeCallAST attrCall;
	private VariableAST variable;
	private OperationCallAST oprCall;
	
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

	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub

	}

}
