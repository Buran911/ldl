package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;
import application.util.Halt;

public class srcExprAST extends NodeAST {
    private IdentifierAST firstId;
    private List<IdentifierAST> secondIds;
    private SetAST set;
    private LiteralAST literal;

    {
	secondIds = new LinkedList<IdentifierAST>();
    }

    public void setFirstId(IdentifierAST firstId) {
	this.firstId = firstId;
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

    @Override
    public Object clone() {
	srcExprAST copy = new srcExprAST();
	if (firstId != null) {
	    copy.setFirstId((IdentifierAST) firstId.clone());
	}
	if (secondIds != null) {
	    for (IdentifierAST identifierAST : secondIds) {
		copy.addSecondId((IdentifierAST) identifierAST.clone());
	    }
	}
	if (set != null) {
	    copy.setSet((SetAST) set.clone());
	}
	if (literal != null) {
	    copy.setLiteral((LiteralAST) literal.clone());
	}

	return copy;
    }

    public List<IdentifierAST> getSecondIds() {
	return secondIds;
    }

    public IdentifierAST getSecondId() {
	if (secondIds.size() > 1){
	    //TODO Придумать название
	    throw new Halt();
	}
	return secondIds.get(0);
    }

    public void addSecondId(IdentifierAST secondId) {
	this.secondIds.add(secondId);
    }
}
