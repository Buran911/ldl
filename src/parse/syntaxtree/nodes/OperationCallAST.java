package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.NodeAST;
import parse.util.Positionable;

public class OperationCallAST extends NodeAST implements Positionable {
    private IdentifierAST identifier;
    private ParametresAST parametres;
    private Integer lineNo;
    private Integer columnNo;

    public void setIdentifier(IdentifierAST identifier) {
	this.identifier = identifier;
	addSuccessor(identifier);
    }

    public IdentifierAST getIdentifier() {
	return identifier;
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
    public OperationCallAST clone() {
	OperationCallAST copy = new OperationCallAST();

	copy.setIdentifier(identifier.clone());
	if (parametres != null) {
	    copy.parametres = parametres.clone();
	}
	copy.lineNo = (lineNo != null) ? lineNo : null;
	copy.columnNo = (columnNo != null) ? columnNo : null;

	return copy;
    }

}
