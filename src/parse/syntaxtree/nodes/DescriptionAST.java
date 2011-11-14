package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.NodeAST;
import parse.util.Positionable;

public class DescriptionAST extends NodeAST implements Positionable, Cloneable {
    private IdentifierAST identifier;
    private TypeAST type;
    private Integer lineNo;
    private Integer columnNo;

    public void setIdentifier(IdentifierAST identifier) {
	this.identifier = identifier;
	addSuccessor(identifier);
    }

    public void setType(TypeAST type) {
	this.type = type;
	addSuccessor(type);
    }

    public IdentifierAST getIdentifier() {
	return identifier;
    }

    public TypeAST getType() {
	return type;
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

    public DescriptionAST clone() {
	DescriptionAST copy = new DescriptionAST();
	copy.identifier = this.identifier.clone();
	copy.type = this.type.clone();
	copy.lineNo = new Integer(this.lineNo);
	copy.columnNo = new Integer(this.columnNo);
	return copy;
    }

}
