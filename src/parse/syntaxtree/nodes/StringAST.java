package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.Datable;
import parse.util.Positionable;

public class StringAST extends LiteralAST implements Datable, Positionable {
    private String string;
    private Integer lineNo;
    private Integer columnNo;

    public StringAST(String string, Integer lineNo, Integer columnNo) {
	this.lineNo = lineNo;
	this.columnNo = columnNo;
	this.string = string;
    }

    public StringAST(StringAST str){
	this.string = str.string;
	this.lineNo = str.lineNo;
	this.columnNo = this.columnNo;
    }
    
    public String getString() {
	return string;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    @Override
    public String getData() {
	return string;
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
	StringAST copy = new StringAST(new String(string), new Integer(lineNo), new Integer(columnNo));
	
	return copy;
    }
}
