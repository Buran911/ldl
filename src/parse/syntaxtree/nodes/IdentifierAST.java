package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.Data;
import parse.syntaxtree.NodeAST;

public class IdentifierAST extends NodeAST implements Data{
	private String id;
	
	public IdentifierAST(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);
		
	}

	@Override
	public String getData() {
		return id;
	}

}
