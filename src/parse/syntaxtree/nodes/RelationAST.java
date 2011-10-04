package parse.syntaxtree.nodes;

import generation.languageconstants.Ratio;
import generation.walkers.TreeWalker;
import parse.syntaxtree.Data;
import parse.syntaxtree.NodeAST;

public class RelationAST extends NodeAST  implements Data{
	private Ratio ratio;
	
	public RelationAST(Ratio ratio) {
		this.ratio = ratio;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

	@Override
	public String getData() {
		return ratio.toString();
	}

}
