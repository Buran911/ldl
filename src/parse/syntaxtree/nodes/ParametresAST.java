package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.NodeAST;

public class ParametresAST extends NodeAST implements Cloneable {

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    public ParametresAST clone() {
	ParametresAST copy = new ParametresAST();
	return copy;
    }
}
