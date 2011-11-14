package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.Datable;
import parse.syntaxtree.NodeAST;
import parse.util.Positionable;

public class IdentifierAST extends NodeAST implements Datable, Positionable, Cloneable {
    private String id;
    private Integer lineNo;
    private Integer columnNo;

    public IdentifierAST(String id, Integer lineNo, Integer columnNo) {
	this.lineNo = lineNo;
	this.columnNo = columnNo;
	this.id = id;
    }

    public String getId() {
	return id;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    @Override
    public String getData() {
	return id;
    }

    @Override
    public Integer getLineNo() {
	return lineNo;
    }

    @Override
    public Integer getColumnNo() {
	return columnNo;
    }
    @Override
    public IdentifierAST clone(){
	IdentifierAST copy = new IdentifierAST(new String(id),new Integer(lineNo),new Integer(columnNo));
	return copy;
    }
}
