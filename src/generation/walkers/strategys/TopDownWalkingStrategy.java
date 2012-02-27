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

/**
 * Базовая стратегия обхода дерева. Сначала обходятся листья, последней -
 * вершина.
 * 
 * @author hindu
 * */
public class TopDownWalkingStrategy extends WalkerStrategy {

    @Override
    public void accept(TreeWalker walker, AttributeCallAST attrCall) {
	walker.visit(attrCall);

	if (attrCall.getAttrCall() != null) {
	    attrCall.getAttrCall().accept(walker);
	}

	if (attrCall.getVariable() != null) {
	    attrCall.getVariable().accept(walker);
	}

	if (attrCall.getIdentifier() != null) {
	    attrCall.getIdentifier().accept(walker);
	}
    }

    @Override
    public void accept(TreeWalker walker, BinaryExpressionAST binaryExp) {
	walker.visit(binaryExp);

	binaryExp.getFirstExpression().accept(walker);
	binaryExp.getRelation().accept(walker);
	binaryExp.getSecondExpression().accept(walker);
    }

    @Override
    public void accept(TreeWalker walker, BinaryOpAST binaryOp) {
	walker.visit(binaryOp);

	binaryOp.getBinary().accept(walker);
	binaryOp.getSetOp().accept(walker);
	binaryOp.getBinaryExp().accept(walker);
    }

    @Override
    public void accept(TreeWalker walker, BooleanAST bool) {
	walker.visit(bool);
    }

    @Override
    public void accept(TreeWalker walker, ConditionAST condition) {
	walker.visit(condition);

	condition.getCondition().accept(walker);

	for (ConstraintAST constraint : condition.getConstraints()) {
	    constraint.accept(walker);
	}

	for (ConstraintAST constraint : condition.getEqClasseses()) {
	    constraint.accept(walker);
	}
    }

    @Override
    public void accept(TreeWalker walker, ContextAST context) {
	walker.visit(context);

	context.getContextName().accept(walker);

	for (DescriptionAST desc : context.getDescriptions()) {
	    desc.accept(walker);
	}

	for (SourceAST src : context.getSources()) {
	    src.accept(walker);
	}

	for (ConstraintAST constraint : context.getConstraints()) {
	    constraint.accept(walker);
	}

	for (EqClassAST eqClass : context.getEqClasseses()) {
	    eqClass.accept(walker);
	}
    }

    @Override
    public void accept(TreeWalker walker, DescriptionAST description) {
	walker.visit(description);

	description.getIdentifier().accept(walker);
	description.getType().accept(walker);
    }

    @Override
    public void accept(TreeWalker walker, EqClassAST eqClass) {
	walker.visit(eqClass);

	for (ConstraintAST constraint : eqClass.getConstraints()) {
	    constraint.accept(walker);
	}
    }

    @Override
    public void accept(TreeWalker walker, FormalParamsAST formalParams) {
	// XXX здесь будет жить код
	walker.visit(formalParams);
    }

    @Override
    public void accept(TreeWalker walker, FunctionalPartAST funcPart) {
	walker.visit(funcPart);

	if (funcPart.getGroup() != null) {
	    funcPart.getGroup().accept(walker);
	}
	if (funcPart.getPriority() != null) {
	    funcPart.getPriority().accept(walker);
	}
    }

    @Override
    public void accept(TreeWalker walker, IdentifierAST id) {
	walker.visit(id);
    }

    @Override
    public void accept(TreeWalker walker, ldlAST ldl) {
	walker.visit(ldl);

	for (ContextAST context : ldl.getContexts()) {
	    context.accept(walker);
	}

	for (PredicateImplAST impl : ldl.getImpls()) {
	    impl.accept(walker);
	}
    }

    @Override
    public void accept(TreeWalker walker, NumberAST number) {
	walker.visit(number);
    }

    @Override
    public void accept(TreeWalker walker, OperationCallAST operationCall) {
	walker.visit(operationCall);

	operationCall.getIdentifier().accept(walker);
    }

    @Override
    public void accept(TreeWalker walker, ParametresAST params) {
	walker.accept(params);
    }

    @Override
    public void accept(TreeWalker walker, PathNameAST pathName) {
	walker.visit(pathName);

	pathName.getContextName().accept(walker);
	pathName.getPredicateName().accept(walker);
    }

    @Override
    public void accept(TreeWalker walker, PredicateAST predicate) {
	walker.visit(predicate);

	if (predicate.getAttrCall() != null) {
	    predicate.getAttrCall().accept(walker);
	}

	if (predicate.getVariable() != null) {
	    predicate.getVariable().accept(walker);
	}

	predicate.getOprCall().accept(walker);
    }

    @Override
    public void accept(TreeWalker walker, PredicateImplAST impl) {
	walker.visit(impl);

	impl.getPathName().accept(walker);
	impl.getFuncPart().accept(walker);
	impl.getFormalParams().accept(walker);

	for (ConstraintAST constraint : impl.getConstraints()) {
	    constraint.accept(walker);
	}
    }

    @Override
    public void accept(TreeWalker walker, RelationAST relation) {
	walker.visit(relation);
    }

    @Override
    public void accept(TreeWalker walker, SetAST set) {
	walker.visit(set);

	for (LiteralAST element : set.getElements()) {
	    element.accept(walker);
	}
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
	walker.visit(src);

	for (srcBlockAST block : src.getSrcBlocks()) {
	    block.accept(walker);
	}
    }

    @Override
    public void accept(TreeWalker walker, srcBlockAST block) {
	walker.visit(block);

	block.getIdentifier().accept(walker);

	for (srcExprAST exprAST : block.getSrcExprs()) {
	    exprAST.accept(walker);
	}
    }

    @Override
    public void accept(TreeWalker walker, srcExprAST expr) {
	walker.visit(expr);

	expr.getFirstId().accept(walker);

	for (IdentifierAST id : expr.getSecondIds()) {
	    id.accept(walker);
	}

	if (expr.getSet() != null) {
	    expr.getSet().accept(walker);
	}

	if (expr.getLiteral() != null) {
	    expr.getLiteral().accept(walker);
	}
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
	walker.visit(var);

	var.getIdentifier().accept(walker);
    }

}
