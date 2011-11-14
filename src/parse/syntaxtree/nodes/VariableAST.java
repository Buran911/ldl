package parse.syntaxtree.nodes;

import parse.util.Positionable;
import generation.idtable.Identifier;
import generation.walkers.TreeWalker;

public class VariableAST extends ExpressionAST implements Positionable, Cloneable {
    private IdentifierAST identifier;
    private Identifier id;
    private Integer lineNo;
    private Integer columnNo;

    public void setIdentifier(IdentifierAST identifier) {
	this.identifier = identifier;
	addSuccessor(identifier);
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
    public VariableAST clone(){
	VariableAST copy = new VariableAST();
	copy.setIdentifier(this.identifier.clone());
	// XXX fuu
	copy.setId(this.id);
	
	copy.lineNo = new Integer(this.lineNo);
	copy.columnNo = new Integer(this.columnNo);
	return copy;
    }
}
