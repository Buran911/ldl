package parse.syntaxtree;

import parse.parsetree.ParseTree;

public class SyntaxTree {
	private NodeAST root;

	public SyntaxTree(ParseTree tree) {
		root = tree.getAST();
		prepare();
	}

	/** Подготавливает дерево к работе, расставляя маркеры
	 *  и расчитывая необходимые значения*/
	private void prepare() {
		//root.accept( new NodeTypeVisitor());
	}
	
	
}
