package parse.syntaxtree.nodes;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class srcBlockAST extends NodeAST {
	private IdentifierAST identifier;
	private List<srcExprAST> srcExprs;
	
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

}
