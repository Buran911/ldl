package generation.walkers.strategys;

import generation.walkers.TreeWalker;
import parse.syntaxtree.nodes.AttributeCallAST;
import parse.syntaxtree.nodes.ConstraintAST;
import parse.syntaxtree.nodes.ContextAST;
import parse.syntaxtree.nodes.DescriptionAST;
import parse.syntaxtree.nodes.EqClassAST;
import parse.syntaxtree.nodes.PredicateImplAST;
import parse.syntaxtree.nodes.SourceAST;

/**
 * Стратегия обхода дерева. Базовый обход - снизу вверх.
 * Чтобы знать namespace в необходимых узлах, некоторые узлы обходятся в другом порядке.
 * @author hindu
 * */
public class IdParsigStrategy extends BottomUpWalkingStrategy {

    @Override
    public void accept(TreeWalker walker, ContextAST context) {
	context.getContextName().accept(walker);

	// Сначала узнаем имя контекста
	walker.visit(context);

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

    // вызовы атрибутов посещаются в обратном порядке для удобства
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
    public void accept(TreeWalker walker, PredicateImplAST impl) {
	impl.getPathName().accept(walker);

	walker.visit(impl);

	impl.getFuncPart().accept(walker);

	if (impl.getFormalParams() != null) {
	    impl.getFormalParams().accept(walker);
	}

	for (ConstraintAST constraint : impl.getConstraints()) {
	    constraint.accept(walker);
	}

    }
}
