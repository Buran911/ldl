package parse.syntaxtree.nodes;

import parse.syntaxtree.NodeAST;

public abstract class ExpressionAST extends NodeAST {
    @Override
    public abstract ExpressionAST clone();
}
