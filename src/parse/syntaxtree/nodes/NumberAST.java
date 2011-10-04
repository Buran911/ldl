package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.Data;

public class NumberAST extends LiteralAST implements Data {
	private Double number;
	
	public NumberAST(Double number) {
		this.number = number;
	}
	
	public Double getNumber() {
		return number;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

	@Override
	public String getData() {
		return number.toString();
	}

}
