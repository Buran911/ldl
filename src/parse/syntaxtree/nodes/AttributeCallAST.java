package parse.syntaxtree.nodes;

import parse.util.Positionable;
import generation.idtable.Identifier;
import generation.walkers.TreeWalker;

public class AttributeCallAST extends ExpressionAST implements Positionable{
	private AttributeCallAST attrCall;
	private VariableAST variable;
	private IdentifierAST identifier;
	private Identifier id;
	private Integer lineNo;
	private Integer columnNo;
	
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

	@Override
	public Integer getLineNo() {
		return lineNo;
	}

	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public Integer getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(Integer columnNo) {
		this.columnNo = columnNo;
	}
}
