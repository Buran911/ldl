package parse.syntaxtree.nodes;

import parse.syntaxtree.NodeAST;

public abstract class ExpressionAST extends NodeAST implements Cloneable{
    public abstract ExpressionAST clone();
}
