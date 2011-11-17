package parse.syntaxtree;

import java.io.Serializable;

import generation.walkers.TreeWalker;
import parse.parsetree.ParseTree;

/**
 * ÀÑÒ.
 * @author hindu
 * */
public class SyntaxTree implements Serializable {
    private NodeAST root;

    public SyntaxTree(ParseTree tree) {
	root = tree.getAST();
    }

    public void accept(TreeWalker walker) {
	root.accept(walker);
    }

}
