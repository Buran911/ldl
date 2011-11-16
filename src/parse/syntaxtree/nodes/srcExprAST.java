package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.NodeAST;

public class srcExprAST extends NodeAST implements Cloneable {
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
	walker.accept(this);

    }

    public srcExprAST clone() {
	srcExprAST copy = new srcExprAST();
	if (firstId != null) {
	    copy.setFirstId(firstId.clone());
	}
	if (secondId != null) {
	    copy.setSecondId(secondId.clone());
	}
	if (set != null) {
	    copy.setSet(set.clone());
	}
	if (literal != null) {
	    copy.setLiteral((LiteralAST) literal.clone());
	}

	return copy;
    }
}
