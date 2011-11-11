package generation.walkers.walkers;

import generation.idtable.IdTable;
import generation.walkers.TreeWalker;
import generation.walkers.WalkerStrategy;
import parse.errhandler.ErrorClass;
import parse.errhandler.ErrorHandler;
import parse.errhandler.ErrorType;
import parse.errhandler.ParseError;
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

/**
 * Проверка типов выражений при сравнивании 
 * TODO проверить FormalParamsAST 
 * TODO проверить ParametresAST
 * TODO проверить ElementsAST
 * TODO определить системные типы
 * */

public class TypeMismatchChecker extends TreeWalker {
    private ErrorHandler errh;
    private final String STR = "String";
    private final String INT = "Integer";
    private final String BOOL = "Boolean";

    public TypeMismatchChecker(WalkerStrategy strategy, ErrorHandler errh) {
	super(strategy);
	this.errh = errh;
    }

    @Override
    public void visit(AttributeCallAST attrCall) {
	// Empty

    }

    @Override
    public void visit(BinaryExpressionAST binaryExp) {
	// Left-side part of expression
	String leftVar = null;
	// Right-side part of expression
	String rightVar = null;

	// Определяем тип первого выражения
	if (binaryExp.getFirstExpression() instanceof AttributeCallAST)
	    leftVar = ((AttributeCallAST) binaryExp.getFirstExpression()).getId().getType();
	else if (binaryExp.getFirstExpression() instanceof VariableAST)
	    leftVar = ((VariableAST) binaryExp.getFirstExpression()).getId().getType();
	else if (binaryExp.getFirstExpression() instanceof StringAST)
	    leftVar = STR;
	else if (binaryExp.getFirstExpression() instanceof NumberAST)
	    leftVar = INT;
	else if (binaryExp.getFirstExpression() instanceof BooleanAST)
	    leftVar = BOOL;

	// Определяем тип второго выражения
	if (binaryExp.getSecondExpression() instanceof AttributeCallAST)
	    rightVar = ((AttributeCallAST) binaryExp.getSecondExpression()).getId().getType();
	else if (binaryExp.getSecondExpression() instanceof VariableAST)
	    rightVar = ((VariableAST) binaryExp.getSecondExpression()).getId().getType();
	else if (binaryExp.getSecondExpression() instanceof StringAST)
	    rightVar = STR;
	else if (binaryExp.getSecondExpression() instanceof NumberAST)
	    rightVar = INT;
	else if (binaryExp.getSecondExpression() instanceof BooleanAST)
	    rightVar = BOOL;

	if (!leftVar.contentEquals(rightVar))
	    errh.addError(new ParseError(ErrorClass.semantic, ErrorType.UncompatibleTypes, binaryExp.getRelation().getLineNo(), binaryExp.getRelation().getColumnNo()));
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
	// Empty

    }

    @Override
    public void visit(DescriptionAST description) {
	// Empty

    }

    @Override
    public void visit(EqClassAST eqClass) {
	// Empty

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

    @Override
    public void visit(ldlAST ldl) {
	// Empty

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
