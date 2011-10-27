package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.NodeAST;
import parse.util.Positionable;

public class OperationCallAST extends NodeAST implements Positionable{
	private IdentifierAST identifier;
	private ParametresAST parametres;
	private Integer lineNo;
	private Integer columnNo;
	
	public void setIdentifier(IdentifierAST identifier) {
		this.identifier = identifier;
		addSuccessor(identifier);
	}
	
	public IdentifierAST getIdentifier() {
		return identifier;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}
	
	@Override
	public Integer getLineNo() {
		return lineNo;
	}

	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public Integer getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(Integer columnNo) {
		this.columnNo = columnNo;
	}

}
