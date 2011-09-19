package parse.parsetree.nodes;

import parse.parsetree.Node;

public class SimpleName extends Node {
	private String name;

	public SimpleName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
