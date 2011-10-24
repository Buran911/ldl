package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.Data;
import parse.syntaxtree.NodeAST;

public class TypeAST extends NodeAST implements Data{
	private String type;
	
	public TypeAST(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}
	@Override
	public String getData() {
		return type;
	}

}
