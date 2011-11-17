package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;

public class SetAST extends NodeAST {
    private List<LiteralAST> elements;

    {
	elements = new LinkedList<LiteralAST>();
    }

    public void addElement(LiteralAST element) {
	elements.add(element);
	addSuccessor(element);
    }

    public List<LiteralAST> getElements() {
	return elements;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    @Override
    public Object clone() {
	SetAST copy = new SetAST();
	for (LiteralAST element : elements) {
	    copy.addElement((LiteralAST) element.clone());
	}
	
	return copy;
    }
}
