package parse.syntaxtree.nodes;

import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class srcExprAST extends NodeAST {
	private IdentifierAST firstId;
	private IdentifierAST secondId;
	private SetAST set;
	private LiteralAST literal;
	
	public void setFirstId(IdentifierAST firstId) {
		this.firstId = firstId;
	}


	public void setSecondId(IdentifierAST secondId) {
		this.secondId = secondId;
	}

	public void setSet(SetAST set) {
		this.set = set;
		addSuccessor(set);
	}

	public void setLiteral(LiteralAST literal) {
		this.literal = literal;
		addSuccessor(literal);
	}



	public IdentifierAST getFirstId() {
		return firstId;
	}


	public IdentifierAST getSecondId() {
		return secondId;
	}


	public SetAST getSet() {
		return set;
	}


	public LiteralAST getLiteral() {
		return literal;
	}


	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub

	}

}
