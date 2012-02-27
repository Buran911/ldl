package generation.walkers.walkers;

import generation.templateengine.EqualityClass;
import generation.templateengine.QueryConstraints;
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
 * Волкер обрабатывает классы эквивалентности, раскидывая ограничения по запросам.
 * @author hindu
 * */
public class TemplateEqClassesFiller extends TreeWalker {
    private EqualityClass constPart;
    private QueryConstraints queryConstraints;

    {
	constPart = new EqualityClass();
    }
    
    public TemplateEqClassesFiller( QueryConstraints queryConstraints) {
	super();
	this.queryConstraints = queryConstraints;
    }

    public TemplateEqClassesFiller(WalkerStrategy strategy, QueryConstraints queryConstraints) {
	super(strategy);
	this.queryConstraints = queryConstraints;
    }
    


    @Override
    public void visit(AttributeCallAST attrCall) {
	// Empty

    }

    @Override
    public void visit(BinaryExpressionAST binaryExp) {
	// Empty

    }

    @Override
    public void visit(BinaryOpAST binaryOp) {
	// Empty

    }

    @Override
    public void visit(BooleanAST bool) {
	// Empty

    }

    @Override
    public void visit(ConditionAST condition) {
	// Empty

    }

    @Override
    public void visit(ContextAST context) {
	constPart.addConstraints(context.getConstraints());

    }

    @Override
    public void visit(DescriptionAST description) {
	// Empty

    }

    @Override
    public void visit(EqClassAST eqClass) {
	int constrCount = eqClass.getConstraints().size();
	int eqClassesCount = queryConstraints.getEqClassesCount();

	// Первый проход, добавляем одному ограничению в пустые классы
	if (eqClassesCount == 0) {
	    eqClassesCount = 1;
	    queryConstraints.addEqualityClass(new EqualityClass());
	}

	queryConstraints.duplicate(constrCount);

	for (int i = 0; i < constrCount; i++) {
	    ConstraintAST constraint = eqClass.getConstraint(i);

	    for (int j = 0; j < eqClassesCount; j++) {
		EqualityClass ec = queryConstraints.getEqClass(j + i * eqClassesCount);
		ec.addConstraint(constraint);
	    }
	}

    }

    @Override
    public void visit(FormalParamsAST formalParams) {
	// Empty

    }

    @Override
    public void visit(FunctionalPartAST funcPart) {
	// Empty

    }

    @Override
    public void visit(IdentifierAST id) {
	// Empty

    }

    // Вызывается в конце обхода при Bottom-Up стратегии обхода
    @Override
    public void visit(ldlAST ldl) {
	queryConstraints.setConstPart(constPart);
	queryConstraints.makeUnmodifiable();
    }

    @Override
    public void visit(NumberAST number) {
	// Empty

    }

    @Override
    public void visit(OperationCallAST operationCall) {
	// Empty

    }

    @Override
    public void visit(ParametresAST params) {
	// Empty

    }

    @Override
    public void visit(PathNameAST pathName) {
	// Empty

    }

    @Override
    public void visit(PredicateAST predicate) {
	// Empty

    }

    @Override
    public void visit(PredicateImplAST impl) {
	// Empty

    }

    @Override
    public void visit(RelationAST relation) {
	// Empty

    }

    @Override
    public void visit(SetAST set) {
	// Empty

    }

    @Override
    public void visit(SetOpAST setOp) {
	// Empty

    }

    @Override
    public void visit(SimpleNameAST simpleName) {
	// Empty

    }

    @Override
    public void visit(SourceAST src) {
	// Empty

    }

    @Override
    public void visit(srcBlockAST block) {
	// Empty

    }

    @Override
    public void visit(srcExprAST expr) {
	// Empty

    }

    @Override
    public void visit(StringAST string) {
	// Empty

    }

    @Override
    public void visit(TypeAST type) {
	// Empty

    }

    @Override
    public void visit(VariableAST var) {
	// Empty

    }

}
