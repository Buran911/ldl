package parse.parsetree.nodes;

import parse.parsetree.Node;

public class LString extends Node {
	private String string;

	public LString(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}
}
