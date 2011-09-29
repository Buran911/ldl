package parse.syntaxtree.nodes;

import parse.syntaxtree.TreeWalker;

public class BinaryOpAST extends BinaryAST {
	private BinaryAST binary;
	private SetOpAST setOp;
	private BinaryExpAST binaryExp;
	
	public void setBinary(BinaryAST binary) {
		this.binary = binary;
		addSuccessor(binary);
	}

	public void setSetOp(SetOpAST setOp) {
		this.setOp = setOp;
		addSuccessor(setOp);
	}

	public void setBinaryExp(BinaryExpAST binaryExp) {
		this.binaryExp = binaryExp;
		addSuccessor(binaryExp);
	}

	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub

	}

}
