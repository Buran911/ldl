package parse.syntaxtree;

import java.io.Serializable;

import generation.walkers.TreeWalker;
import parse.parsetree.ParseTree;

public class SyntaxTree implements Serializable {
	private NodeAST root;

	public SyntaxTree(ParseTree tree) {
		root = tree.getAST();
		prepare();
	}

	public void accept(TreeWalker walker){
		root.accept(walker);
	}
	
	/** Подготавливает дерево к работе, расставляя маркеры
	 *  и расчитывая необходимые значения*/
	private void prepare() {
		//root.accept( new NodeTypeVisitor());
	}
	
	
}
