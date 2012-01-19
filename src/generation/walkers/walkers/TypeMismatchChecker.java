package generation.walkers.walkers;

import generation.walkers.TreeWalker;
import generation.walkers.WalkerStrategy;

import java.util.LinkedList;
import java.util.List;

import parse.errhandler.Checker;
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
 * ¬олкер ищет несоотвестви€ типов. 
 * TODO проверить FormalParamsAST
 * TODO проверить ParametresAST 
 * TODO проверить ElementsAST 
 * TODO определить системные
 * типы
 * 
 * @author exellent
 * */

public class TypeMismatchChecker extends TreeWalker implements Checker {
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
	String leftType = null;
	String leftName = null;
	// Right-side part of expression
	String rightType = null;
	String rightName = null;

	// ќпредел€ем тип первого выражени€
	if (binaryExp.getFirstExpression() instanceof AttributeCallAST) {
	    leftType = ((AttributeCallAST) binaryExp.getFirstExpression()).getId().getType();
	    leftName = ((AttributeCallAST) binaryExp.getFirstExpression()).getId().getName();
	}
	else if (binaryExp.getFirstExpression() instanceof VariableAST) {
	    leftType = ((VariableAST) binaryExp.getFirstExpression()).getId().getType();
	    leftName = ((VariableAST) binaryExp.getFirstExpression()).getId().getName();
	}
	else if (binaryExp.getFirstExpression() instanceof StringAST) {
	    leftType = STR;
	    leftName = ((StringAST) binaryExp.getFirstExpression()).getString();
	}
	else if (binaryExp.getFirstExpression() instanceof NumberAST) {
	    leftType = INT;
	    leftName = ((NumberAST) binaryExp.getFirstExpression()).getNumber().toString();
	}
	else if (binaryExp.getFirstExpression() instanceof BooleanAST) {
	    leftType = BOOL;
	    leftName = ((BooleanAST) binaryExp.getFirstExpression()).getBool().toString();
	}

	// ќпредел€ем тип второго выражени€
	if (binaryExp.getSecondExpression() instanceof AttributeCallAST) {
	    rightType = ((AttributeCallAST) binaryExp.getSecondExpression()).getId().getType();
	    rightName = ((AttributeCallAST) binaryExp.getSecondExpression()).getId().getName();
	}
	else if (binaryExp.getSecondExpression() instanceof VariableAST) {
	    rightType = ((VariableAST) binaryExp.getSecondExpression()).getId().getType();
	    rightName = ((VariableAST) binaryExp.getSecondExpression()).getId().getName();
	}
	else if (binaryExp.getSecondExpression() instanceof StringAST) {
	    rightType = STR;
	    rightName = ((StringAST) binaryExp.getSecondExpression()).getString();
	}
	else if (binaryExp.getSecondExpression() instanceof NumberAST) {
	    rightType = INT;
	    rightName = ((NumberAST) binaryExp.getSecondExpression()).getNumber().toString();
	}
	else if (binaryExp.getSecondExpression() instanceof BooleanAST) {
	    rightType = BOOL;
	    rightName = ((BooleanAST) binaryExp.getSecondExpression()).getBool().toString();
	}

	if (!leftType.contentEquals(rightType))
	    errh.addError(new ParseError(ErrorType.UncompatibleTypes, binaryExp.getRelation().getLineNo(), binaryExp.getRelation().getColumnNo(),
		    "ѕеременные " + leftName + "(" + leftType + ")" + " и " + rightName + "(" + rightType + ")" + " несовместимы при сравнении"));
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
