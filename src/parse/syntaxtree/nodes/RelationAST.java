package parse.syntaxtree.nodes;

import parse.parsetree.Ratio;
import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class RelationAST extends NodeAST {
	private Ratio ratio;
	
	public RelationAST(Ratio ratio) {
		this.ratio = ratio;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

}
