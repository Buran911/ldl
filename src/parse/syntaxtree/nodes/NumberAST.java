package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.Datable;
import parse.util.Positionable;

public class NumberAST extends LiteralAST implements Datable, Positionable {
    private Double number;
    private Integer lineNo;
    private Integer columnNo;

    public NumberAST(Double number, Integer lineNo, Integer columnNo) {
	this.lineNo = lineNo;
	this.columnNo = columnNo;
	this.number = number;
    }

    public Double getNumber() {
	return number;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    @Override
    public String getData() {
	return number.toString();
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
    public Object clone() {
	NumberAST copy = new NumberAST(new Double(number), new Integer(lineNo), new Integer(columnNo));
	return copy;
    }
}
