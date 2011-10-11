package generation.walkers.walkers;

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
import generation.walkers.TreeWalker;
import generation.walkers.WalkerStrategy;

public class TemplateTypeFiller extends TreeWalker {

	public TemplateTypeFiller(WalkerStrategy strategy) {
		super(strategy);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visit(AttributeCallAST attrCall) {
		attrCall.setAttributeCallExp();

	}

	@Override
	public void visit(BinaryExpressionAST binaryExp) {
		binaryExp.setNode();
	}

	@Override
	public void visit(BinaryOpAST binaryOp) {
		binaryOp.setNode();
	}

	@Override
	public void visit(BooleanAST bool) {
		bool.setLeaf();

	}

	@Override
	public void visit(ConditionAST condition) {
		condition.setNode();

	}

	@Override
	public void visit(ContextAST context) {
		context.setNode();

	}

	@Override
	public void visit(DescriptionAST description) {
		description.setDummy();

	}

	@Override
	public void visit(EqClassAST eqClass) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void visit(FormalParamsAST formalParams) {
		formalParams.setDummy();

	}

	@Override
	public void visit(FunctionalPartAST funcPart) {
		funcPart.setDummy();

	}

	@Override
	public void visit(IdentifierAST id) {
		id.setLeaf();

	}

	@Override
	public void visit(ldlAST ldl) {
		ldl.setDummy();

	}

	@Override
	public void visit(NumberAST number) {
		number.setLeaf();

	}

	@Override
	public void visit(OperationCallAST operationCall) {
		operationCall.setFunction();

	}

	@Override
	public void visit(ParametresAST params) {
		params.setDummy();

	}

	@Override
	public void visit(PathNameAST pathName) {
		pathName.setDummy();

	}

	@Override
	public void visit(PredicateAST predicate) {
		predicate.setDummy();

	}

	@Override
	public void visit(PredicateImplAST impl) {
		impl.setNode();

	}

	@Override
	public void visit(RelationAST relation) {
		relation.setLeaf();

	}

	@Override
	public void visit(SetAST set) {
		set.setNode();

	}

	@Override
	public void visit(SetOpAST setOp) {
		setOp.setLeaf();

	}

	@Override
	public void visit(SimpleNameAST simpleName) {
		simpleName.setDummy();

	}

	@Override
	public void visit(SourceAST src) {
		src.setDummy();

	}

	@Override
	public void visit(srcBlockAST block) {
		block.setDummy();

	}

	@Override
	public void visit(srcExprAST expr) {
		expr.setDummy();

	}

	@Override
	public void visit(StringAST string) {
		string.setLeaf();

	}

	@Override
	public void visit(TypeAST type) {
		type.setDummy();

	}

	@Override
	public void visit(VariableAST var) {
		var.setVariableExp();

	}

}
