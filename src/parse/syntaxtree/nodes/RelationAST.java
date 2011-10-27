package parse.syntaxtree.nodes;

import generation.languageconstants.Ratio;
import generation.walkers.TreeWalker;
import parse.syntaxtree.Datable;
import parse.syntaxtree.NodeAST;
import parse.util.Positionable;

public class RelationAST extends NodeAST  implements Datable, Positionable{
	private Ratio ratio;
	private Integer lineNo;
	private Integer columnNo;
	
	public RelationAST(Ratio ratio, Integer lineNo, Integer columnNo) {
		this.lineNo = lineNo;
		this.columnNo = columnNo;
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

	
	@Override
	public Integer getLineNo() {
		return lineNo;
	}

	@Override
	public Integer getColumnNo() {
		return columnNo;
	}
}
