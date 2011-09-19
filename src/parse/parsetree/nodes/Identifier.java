package parse.parsetree.nodes;

import parse.parsetree.Node;

public class Identifier extends Node {
	private String identifier;

	public Identifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}
}
