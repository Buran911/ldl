package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;
import parse.util.Positionable;

public class srcBlockAST extends NodeAST implements Positionable {
	private IdentifierAST identifier;
	private List<srcExprAST> srcExprs;
	private Integer lineNo;
	private Integer columnNo;
	
	{
		srcExprs = new LinkedList<srcExprAST>();
	}
	
	public void setIdentifier(IdentifierAST identifier) {
		this.identifier = identifier;
		addSuccessor(identifier);
	}

	public void addSrcExpr(srcExprAST srcExpr) {
		srcExprs.add(srcExpr);
		addSuccessor(srcExpr);
	}

	public IdentifierAST getIdentifier() {
		return identifier;
	}

	public List<srcExprAST> getSrcExprs() {
		return srcExprs;
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

}
