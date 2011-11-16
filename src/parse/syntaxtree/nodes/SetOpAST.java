package parse.syntaxtree.nodes;

import generation.languageconstants.Logical;
import generation.walkers.TreeWalker;
import parse.syntaxtree.Datable;
import parse.syntaxtree.NodeAST;
import parse.util.Positionable;

public class SetOpAST extends NodeAST implements Datable, Positionable {
    private Logical operation;
    private Integer lineNo;
    private Integer columnNo;

    public SetOpAST(Logical operation, Integer lineNo, Integer columnNo) {
	this.lineNo = lineNo;
	this.columnNo = columnNo;
	this.operation = operation;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    @Override
    public String getData() {
	return operation.value();
    }

    @Override
    public Integer getLineNo() {
	return lineNo;
    }

    @Override
    public Integer getColumnNo() {
	return columnNo;
    }

    public SetOpAST clone() {
	SetOpAST copy = new SetOpAST(operation, new Integer(lineNo), new Integer(columnNo));

	return copy;
    }

}
