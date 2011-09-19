package parse.parsetree.nodes;

import parse.parsetree.Node;

public class Number extends Node {
	private Double number;

	public Number(Double number) {
		this.number = number;
	}

	public Double getNumber() {
		return number;
	}
	
}
