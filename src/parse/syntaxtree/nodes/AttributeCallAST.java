package parse.syntaxtree.nodes;

import parse.syntaxtree.TreeWalker;

public class AttributeCallAST extends ExpressionAST {
	private AttributeCallAST attrCall;
	private VariableAST variable;
	private IdentifierAST identifier;
	
	public void setAttrCall(AttributeCallAST attrCall) {
		this.attrCall = attrCall;
		addSuccessor(attrCall);
	}

	public void setVariable(VariableAST variable) {
		this.variable = variable;
		addSuccessor(variable);
	}

	public void setIdentifier(IdentifierAST identifier) {
		this.identifier = identifier;
		addSuccessor(identifier);
	}

	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub

	}

}
