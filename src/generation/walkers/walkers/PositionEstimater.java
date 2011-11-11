package generation.walkers.walkers;

import parse.parsetree.nodes.BinaryExp;
import parse.syntaxtree.nodes.AttributeCallAST;
import parse.syntaxtree.nodes.BinaryExpressionAST;
import parse.syntaxtree.nodes.BinaryOpAST;
import parse.syntaxtree.nodes.BooleanAST;
import parse.syntaxtree.nodes.ConditionAST;
import parse.syntaxtree.nodes.ContextAST;
import parse.syntaxtree.nodes.DescriptionAST;
import parse.syntaxtree.nodes.EqClassAST;
import parse.syntaxtree.nodes.FormalParamsAST;
import parse.syntaxtree.nodes.FunctionalPartAST;
import parse.syntaxtree.nodes.IdentifierAST;
import parse.syntaxtree.nodes.NumberAST;
import parse.syntaxtree.nodes.OperationCallAST;
import parse.syntaxtree.nodes.ParametresAST;
import parse.syntaxtree.nodes.PathNameAST;
import parse.syntaxtree.nodes.PredicateAST;
import parse.syntaxtree.nodes.PredicateImplAST;
import parse.syntaxtree.nodes.RelationAST;
import parse.syntaxtree.nodes.SetAST;
import parse.syntaxtree.nodes.SetOpAST;
import parse.syntaxtree.nodes.SimpleNameAST;
import parse.syntaxtree.nodes.SourceAST;
import parse.syntaxtree.nodes.StringAST;
import parse.syntaxtree.nodes.TypeAST;
import parse.syntaxtree.nodes.VariableAST;
import parse.syntaxtree.nodes.ldlAST;
import parse.syntaxtree.nodes.srcBlockAST;
import parse.syntaxtree.nodes.srcExprAST;
import generation.idtable.Predicate;
import generation.walkers.TreeWalker;
import generation.walkers.WalkerStrategy;

public class PositionEstimater extends TreeWalker {

	public PositionEstimater(WalkerStrategy strategy) {
		super(strategy);
	}

	@Override
	public void visit(AttributeCallAST attrCall) {
		attrCall.setLineNo(attrCall.getIdentifier().getLineNo());
		attrCall.setColumnNo(attrCall.getIdentifier().getColumnNo());
	}

	@Override
	public void visit(BinaryExpressionAST binaryExp) {
		if(binaryExp.getRelation() != null){
			binaryExp.setLineNo( binaryExp.getRelation().getLineNo());
			binaryExp.setColumnNo(binaryExp.getRelation().getColumnNo());
		}
		
		if(binaryExp.getBool() != null){
			binaryExp.setLineNo( binaryExp.getBool().getLineNo());
			binaryExp.setColumnNo(binaryExp.getBool().getColumnNo());
		}

	}

	@Override
	public void visit(BinaryOpAST binaryOp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(BooleanAST bool) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ConditionAST condition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ContextAST context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(DescriptionAST description) {
		description.setLineNo(description.getIdentifier().getLineNo());
		description.setColumnNo(description.getIdentifier().getColumnNo());

	}

	@Override
	public void visit(EqClassAST eqClass) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FormalParamsAST formalParams) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FunctionalPartAST funcPart) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IdentifierAST id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ldlAST ldl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NumberAST number) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(OperationCallAST operationCall) {
		operationCall.setLineNo(operationCall.getIdentifier().getLineNo());
		operationCall.setColumnNo(operationCall.getIdentifier().getColumnNo());
	}

	@Override
	public void visit(ParametresAST params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(PathNameAST pathName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(PredicateAST predicate) {
	    predicate.setLineNo(predicate.getOprCall().getIdentifier().getLineNo());
	    predicate.setColumnNo(predicate.getOprCall().getIdentifier().getColumnNo());
	}

	@Override
	public void visit(PredicateImplAST impl) {
		impl.setLineNo(impl.getPathName().getContextName().getLineNo());
		impl.setColumnNo(impl.getPathName().getContextName().getColumnNo());
	}

	@Override
	public void visit(RelationAST relation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(SetAST set) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(SetOpAST setOp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(SimpleNameAST simpleName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(SourceAST src) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(srcBlockAST block) {
		block.setLineNo(block.getIdentifier().getLineNo());
		block.setColumnNo(block.getIdentifier().getColumnNo());

	}

	@Override
	public void visit(srcExprAST expr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StringAST string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(TypeAST type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(VariableAST var) {
		var.setLineNo(var.getIdentifier().getLineNo());
		var.setColumnNo(var.getIdentifier().getColumnNo());
	}

}
