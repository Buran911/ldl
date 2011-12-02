package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.Datable;
import parse.util.Positionable;

public class BooleanAST extends LiteralAST implements Datable, Positionable {
    private Boolean bool;
    private Integer lineNo;
    private Integer columnNo;

    public BooleanAST(Boolean bool, Integer lineNo, Integer columnNo) {
	this.lineNo = lineNo;
	this.columnNo = columnNo;
	this.bool = bool;
    }

    public Boolean getBool() {
	return bool;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    @Override
    public String getData() {
	return bool.toString();
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
    public BooleanAST clone() {
	BooleanAST copy = new BooleanAST(new Boolean(bool), new Integer(lineNo), new Integer(columnNo));
	return copy;
    }
}
