package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.Datable;
import parse.syntaxtree.NodeAST;
import parse.util.Positionable;

public class SimpleNameAST extends NodeAST implements Datable, Positionable {
    private String name;
    private Integer lineNo;
    private Integer columnNo;

    public SimpleNameAST(String name, Integer lineNo, Integer columnNo) {
	this.lineNo = lineNo;
	this.columnNo = columnNo;
	this.name = name;
    }

    public String getName() {
	return name;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    @Override
    public String getData() {
	return name;
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
    public SimpleNameAST clone() {
	SimpleNameAST copy = new SimpleNameAST(new String(name), new Integer(lineNo), new Integer(columnNo));
	
	return copy;
    }
}
