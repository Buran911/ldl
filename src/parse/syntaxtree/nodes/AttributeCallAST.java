package parse.syntaxtree.nodes;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import parse.util.Positionable;
import generation.idtable.IdTable;
import generation.idtable.Identifier;
import generation.walkers.TreeWalker;

public class AttributeCallAST extends ExpressionAST implements Positionable {
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

    public Identifier getLastId(IdTable table, String contextName) {
	List<String> attrChain = new LinkedList<String>();
	AttributeCallAST attrFollower = getAttrCall();

	if (attrFollower == null) {
	    attrChain.add(getIdentifier().getData());
	    attrChain.add(getVariable().getIdentifier().getData());
	}
	else {
	    while (attrFollower.getAttrCall() != null) {
		attrChain.add(attrFollower.getIdentifier().getData());
		attrFollower = attrFollower.getAttrCall();
	    }
	    attrChain.add(attrFollower.getIdentifier().getData());
	    attrChain.add(attrFollower.getVariable().getIdentifier().getData());
	}

	Collections.reverse(attrChain);

	String curContext = contextName;
	Identifier id = null;
	for (String idName : attrChain) {
	    id = table.getId(idName, curContext);
	    curContext = id.getType();
	}
	return id;
    }
    
    @Override
    public Object clone() {
	AttributeCallAST copy = new AttributeCallAST();

	if (attrCall != null) {
	    copy.setAttrCall((AttributeCallAST) getAttrCall().clone());
	}
	copy.setVariable(getVariable().clone());
	if (id != null) {
	    copy.id = (Identifier) id.clone();
	}
	copy.setIdentifier((IdentifierAST) getIdentifier().clone());
	copy.lineNo = (lineNo != null) ? lineNo : null;
	copy.columnNo = (columnNo != null) ? columnNo : null;

	return copy;
    }
}
