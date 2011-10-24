package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.Data;

public class StringAST extends LiteralAST implements Data{
	private String string;
	
	public StringAST(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

	@Override
	public String getData() {
		return string;
	}

}
