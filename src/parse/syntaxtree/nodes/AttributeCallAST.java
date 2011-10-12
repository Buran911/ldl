package parse.syntaxtree.nodes;

import generation.idtable.Identifier;
import generation.walkers.TreeWalker;

public class AttributeCallAST extends ExpressionAST {
	private AttributeCallAST attrCall;
	private VariableAST variable;
	private IdentifierAST identifier;
	private Identifier id;
	
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

	public AttributeCallAST getAttrCall() {
		return attrCall;
	}

	public VariableAST getVariable() {
		return variable;
	}

	public IdentifierAST getIdentifier() {
		return identifier;
	}

	public Identifier getId() {
		return id;
	}

	public void setId(Identifier id) {
		this.id = id;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

}
