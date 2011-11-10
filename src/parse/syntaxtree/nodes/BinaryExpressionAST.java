package parse.syntaxtree.nodes;

import parse.util.Positionable;
import generation.walkers.TreeWalker;

public class BinaryExpressionAST extends BinaryExpAST implements Positionable {
	private ExpressionAST firstExpression;
	private RelationAST relation;
	private ExpressionAST secondExpression;
	private BooleanAST bool;
	private Integer lineNo;
	private Integer columnNo;
	
	
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

	public BooleanAST getBool() {
		return bool;
	}

	public void setBool(BooleanAST bool) {
		this.bool = bool;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}
	
	@Override
	public Integer getLineNo() {
		return lineNo;
	}

	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public Integer getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(Integer columnNo) {
		this.columnNo = columnNo;
	}

}
