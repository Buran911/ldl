package parse.syntaxtree;

import generation.walkers.TreeWalker;

import java.io.Serializable;

import parse.parsetree.ParseTree;
import parse.syntaxtree.nodes.ldlAST;


public class SyntaxTree implements Cloneable{

    private NodeAST root;

    public SyntaxTree(ParseTree tree) {
	root = tree.getAST();
	prepare();
    }
    public SyntaxTree(NodeAST root){
	this.root = root;
    }
    @Override
    public Object clone() {
	NodeAST rootcopy = root.clone();
	
	System.out.print("return");
	return new SyntaxTree(rootcopy);
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
