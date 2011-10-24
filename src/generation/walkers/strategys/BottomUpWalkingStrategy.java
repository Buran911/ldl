package generation.walkers.strategys;

import generation.walkers.TreeWalker;
import generation.walkers.WalkerStrategy;
import parse.syntaxtree.nodes.AttributeCallAST;
import parse.syntaxtree.nodes.BinaryExpressionAST;
import parse.syntaxtree.nodes.BinaryOpAST;
import parse.syntaxtree.nodes.BooleanAST;
import parse.syntaxtree.nodes.ConditionAST;
import parse.syntaxtree.nodes.ConstraintAST;
import parse.syntaxtree.nodes.ContextAST;
import parse.syntaxtree.nodes.DescriptionAST;
import parse.syntaxtree.nodes.EqClassAST;
import parse.syntaxtree.nodes.FormalParamsAST;
import parse.syntaxtree.nodes.FunctionalPartAST;
import parse.syntaxtree.nodes.IdentifierAST;
import parse.syntaxtree.nodes.LiteralAST;
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

public class BottomUpWalkingStrategy extends WalkerStrategy {

	@Override
	public void accept(TreeWalker walker, AttributeCallAST attrCall) {
		if(attrCall.getAttrCall() != null){
			attrCall.getAttrCall().accept(walker);
		}
		
		if(attrCall.getVariable() != null){
			attrCall.getVariable().accept(walker);
		}
		
		if(attrCall.getIdentifier() != null){
			attrCall.getIdentifier().accept(walker);
		}
		
		walker.visit(attrCall);
	}

	@Override
	public void accept(TreeWalker walker, BinaryExpressionAST binaryExp) {
		binaryExp.getFirstExpression().accept(walker);
		binaryExp.getRelation().accept(walker);
		binaryExp.getSecondExpression().accept(walker);
		
		walker.visit(binaryExp);

	}

	@Override
	public void accept(TreeWalker walker, BinaryOpAST binaryOp) {
		binaryOp.getBinary().accept(walker);
		binaryOp.getSetOp().accept(walker);
		binaryOp.getBinaryExp().accept(walker);
		
		walker.visit(binaryOp);
	}

	@Override
	public void accept(TreeWalker walker, BooleanAST bool) {
		walker.visit(bool);
	}

	@Override
	public void accept(TreeWalker walker, ConditionAST condition) {
		condition.getCondition().accept(walker);
		
		for(ConstraintAST constraint : condition.getConstraints()){
			constraint.accept(walker);
		}
		
		for(ConstraintAST constraint : condition.getEqClasseses()){
			constraint.accept(walker);
		}
		
		walker.visit(condition);
	}

	@Override
	public void accept(TreeWalker walker, ContextAST context) {
		context.getContextName().accept(walker);
		
		for(DescriptionAST desc : context.getDescriptions()){
			desc.accept(walker);
		}
		
		for(SourceAST src : context.getSources()){
			src.accept(walker);
		}
		
		for(ConstraintAST constraint : context.getConstraints()){
			constraint.accept(walker);
		}
		
		for(EqClassAST eqClass : context.getEqClasseses()){
			eqClass.accept(walker);
		}
		
		walker.visit(context);
	}

	@Override
	public void accept(TreeWalker walker, DescriptionAST description) {
		description.getIdentifier().accept(walker);
		description.getType().accept(walker);

		walker.visit(description);
	}

	@Override
	public void accept(TreeWalker walker, EqClassAST eqClass) {
		for(ConstraintAST constraint : eqClass.getConstraints()){
			constraint.accept(walker);
		}

		walker.visit(eqClass);
	}
	
	@Override
	public void accept(TreeWalker walker, FormalParamsAST formalParams) {
		// XXX здесь будет жить код
		walker.visit(formalParams);
	}

	@Override
	public void accept(TreeWalker walker, FunctionalPartAST funcPart) {
		if(funcPart.getGroup() != null){
			funcPart.getGroup().accept(walker);
		} 
		if(funcPart.getPriority() != null){
			funcPart.getPriority().accept(walker);
		}
		
		walker.visit(funcPart);
	}

	@Override
	public void accept(TreeWalker walker, IdentifierAST id) {
		walker.visit(id);
	}

	@Override
	public void accept(TreeWalker walker, ldlAST ldl) {
		for(ContextAST context : ldl.getContexts()){
			context.accept(walker);
		}
		
		for(PredicateImplAST impl : ldl.getImpls()){
			impl.accept(walker);
		}
		
		walker.visit(ldl);
	}

	@Override
	public void accept(TreeWalker walker, NumberAST number) {
		walker.visit(number);
	}

	@Override
	public void accept(TreeWalker walker, OperationCallAST operationCall) {
		operationCall.getIdentifier().accept(walker);
		
		walker.visit(operationCall);
	}

	@Override
	public void accept(TreeWalker walker, ParametresAST params) {
		walker.accept(params);
	}

	@Override
	public void accept(TreeWalker walker, PathNameAST pathName) {
		pathName.getContextName().accept(walker);
		pathName.getPredicateName().accept(walker);

		walker.visit(pathName);
	}

	@Override
	public void accept(TreeWalker walker, PredicateAST predicate) {
		if(predicate.getAttrCall() != null){
			predicate.getAttrCall().accept(walker);
		}
		
		if(predicate.getVariable() != null){
			predicate.getVariable().accept(walker);
		}
		
		predicate.getOprCall().accept(walker);
		
		walker.visit(predicate);

	}

	@Override
	public void accept(TreeWalker walker, PredicateImplAST impl) {
		impl.getPathName().accept(walker);
		impl.getFuncPart().accept(walker);
		
		if(impl.getFormalParams() != null){
			impl.getFormalParams().accept(walker);
		}
		
		for(ConstraintAST constraint : impl.getConstraints()){
			constraint.accept(walker);
		}
		
		walker.visit(impl);
	}

	@Override
	public void accept(TreeWalker walker, RelationAST relation) {
		walker.visit(relation);
	}

	@Override
	public void accept(TreeWalker walker, SetAST set) {
		for(LiteralAST element : set.getElements()){
			element.accept(walker);
		}

		walker.visit(set);
	}

	@Override
	public void accept(TreeWalker walker, SetOpAST setOp) {
		walker.visit(setOp);
	}

	@Override
	public void accept(TreeWalker walker, SimpleNameAST simpleName) {
		walker.visit(simpleName);

	}

	@Override
	public void accept(TreeWalker walker, SourceAST src) {
		for(srcBlockAST block : src.getSrcBlocks()){
			block.accept(walker);
		}

		walker.visit(src);
	}

	@Override
	public void accept(TreeWalker walker, srcBlockAST block) {
		block.getIdentifier().accept(walker);
		
		for(srcExprAST exprAST : block.getSrcExprs()){
			exprAST.accept(walker);
		}
		
		walker.visit(block);
	}

	@Override
	public void accept(TreeWalker walker, srcExprAST expr) {
		expr.getFirstId().accept(walker);
		
		if(expr.getSecondId() != null ){
			expr.getSecondId().accept(walker);
		}
		
		if(expr.getSet() != null ){
			expr.getSet().accept(walker);
		}
		
		if(expr.getLiteral() != null ){
			expr.getLiteral().accept(walker);
		}

		walker.visit(expr);
	}

	@Override
	public void accept(TreeWalker walker, StringAST string) {
		walker.visit(string);

	}

	@Override
	public void accept(TreeWalker walker, TypeAST type) {
		walker.visit(type);

	}

	@Override
	public void accept(TreeWalker walker, VariableAST var) {
		var.getIdentifier().accept(walker);

		walker.visit(var);
	}

}
