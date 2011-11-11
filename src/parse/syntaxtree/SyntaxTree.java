package parse.syntaxtree;

import generation.walkers.TreeWalker;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

import parse.parsetree.ParseTree;

public class SyntaxTree implements Serializable {
    private NodeAST root;

    public SyntaxTree(ParseTree tree) {
	root = tree.getAST();
	prepare();
    }

    public SyntaxTree clone() {
	SyntaxTree tt;
	NodeAST node;
	ListIterator it;
	LinkedList LL;
	node = root;

	{
	    LL = node.getSuccessors();
	    it = LL.listIterator();
	    while (it.hasNext()) {
		it.next();
	    }
	}
	
	System.out.print("return");
	return null;
    }

    public void accept(TreeWalker walker) {
	root.accept(walker);
    }

    /**
     * Подготавливает дерево к работе, расставляя маркеры и расчитывая
     * необходимые значения
     */
    private void prepare() {
	// root.accept( new NodeTypeVisitor());
    }

    protected NodeAST getRoot() {
	return root;
    }

}
