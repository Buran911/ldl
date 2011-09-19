package parse.parsetree.nodes;

import parse.parsetree.Node;

public class Type extends Node {
	private String type;

	public Type(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
}
