package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

public class BinaryExpressionAST extends BinaryExpAST {
	private ExpressionAST firstExpression;
	private RelationAST relation;
	private ExpressionAST secondExpression;
	
	public void setFirstExpression(ExpressionAST firstExpression) {
		this.firstExpression = firstExpression;
		addSuccessor(firstExpression);
	}

	public void setRelation(RelationAST relation) {
		this.relation = relation;
		addSuccessor(relation);
	}

	public void setSecondExpression(ExpressionAST secondExpression) {
		this.secondExpression = secondExpression;
		addSuccessor(secondExpression);
	}

	public ExpressionAST getFirstExpression() {
		return firstExpression;
	}

	public RelationAST getRelation() {
		return relation;
	}

	public ExpressionAST getSecondExpression() {
		return secondExpression;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

}
