package parse.parsetree.nodes;

import parse.parsetree.Node;

public class Bool extends Node {
	private boolean bool;

	public Bool(boolean bool) {
		this.bool = bool;
	}

	public boolean getBool() {
		return bool;
	}
}
