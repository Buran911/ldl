package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.syntaxtree.NodeAST;

public class ParametresAST extends NodeAST {

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    @Override
    public Object clone() {
	ParametresAST copy = new ParametresAST();
	
	return copy;
    }
}
