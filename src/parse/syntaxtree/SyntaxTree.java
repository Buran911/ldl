package parse.syntaxtree;

import generation.walkers.TreeWalker;
import parse.parsetree.ParseTree;

/**
 * ÀÑÒ.
 * 
 * @author hindu
 * */
public class SyntaxTree implements Cloneable {
    private NodeAST root;

    public SyntaxTree(ParseTree tree) {
	root = tree.getAST();
    }

    public SyntaxTree(NodeAST root) {
	this.root = root;
    }

    public void accept(TreeWalker walker) {
	root.accept(walker);
    }

    @Override
    public Object clone() {
	NodeAST rootcopy = (NodeAST) root.clone();
	return new SyntaxTree(rootcopy);
    }

    protected NodeAST getRoot() {
	return root;
    }
}
