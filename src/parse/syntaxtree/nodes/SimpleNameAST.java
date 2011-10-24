package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.Data;
import parse.syntaxtree.NodeAST;

public class SimpleNameAST extends NodeAST implements Data {
	private String name;
	
	public SimpleNameAST(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

	@Override
	public String getData() {
		return name;
	}

}
