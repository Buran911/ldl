package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.Datable;
import parse.syntaxtree.NodeAST;
import parse.util.Positionable;

public class TypeAST extends NodeAST implements Datable, Positionable{
	private String type;
	private Integer lineNo;
	private Integer columnNo;
	
	public TypeAST(String type, Integer lineNo, Integer columnNo) {
		this.lineNo = lineNo;
		this.columnNo = columnNo;
		this.type = type;
	}
	public String getType() {
		return type;
	}
	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}
	@Override
	public String getData() {
		return type;
	}
	@Override
	public Integer getLineNo() {
		return lineNo;
	}

	@Override
	public Integer getColumnNo() {
		return columnNo;
	}
	public TypeAST clone(){
	    TypeAST copy = new TypeAST(new String(type),new Integer(lineNo), new Integer(columnNo));
	    return copy;
	}
}
