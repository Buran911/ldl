package parse.syntaxtree.nodes;

public abstract class BinaryAST extends ConstraintAST {
    Boolean not;

    {
	not = false;
    }
    @Override
    public abstract BinaryAST clone();
}
