package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

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

    public BinaryAST getBinary() {
	return binary;
    }

    public SetOpAST getSetOp() {
	return setOp;
    }

    public BinaryExpAST getBinaryExp() {
	return binaryExp;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    @Override
    public Object clone() {
	BinaryOpAST copy = new BinaryOpAST();
	copy.setBinary((BinaryAST) binary.clone());
	copy.setSetOp((SetOpAST) setOp.clone());
	copy.setBinaryExp((BinaryExpAST) binaryExp.clone());

	return copy;
    }

}
