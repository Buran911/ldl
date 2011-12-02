package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.NodeAST;
import parse.util.Positionable;

public class DescriptionAST extends NodeAST implements Positionable {
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
    
    @Override
    public DescriptionAST clone() {
	DescriptionAST copy = new DescriptionAST();
	copy.identifier = identifier.clone();
	copy.type = type.clone();
	copy.lineNo = (lineNo != null) ? lineNo : null;
	copy.columnNo = (columnNo != null) ? columnNo : null;
	return copy;
    }

}
