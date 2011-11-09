package generation.walkers.walkers;

import generation.idtable.IdTable;
import generation.idtable.Identifier;
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

public class TypeMismatch extends TreeWalker {
    private IdTable table;
    private String contextName;
    private ErrorHandler errh;

    public TypeMismatch(WalkerStrategy strategy, IdTable table, ErrorHandler errh) {
	super(strategy);
	this.table = table;
	this.errh = errh;
	System.out.println("TM constr");

    }

    @Override
    public void visit(AttributeCallAST attrCall) {
	// TODO Auto-generated method stub

    }

    @Override
    public void visit(BinaryExpressionAST binaryExp) {
	System.out.println("In binaryExp");
	String leftVar = null;
	String rightVar = null;
	// first expr

	if (binaryExp.getFirstExpression() instanceof AttributeCallAST) {
	    leftVar = ((AttributeCallAST) binaryExp.getFirstExpression()).getId().getType();
	    System.out.println("FirstAtt");
	}
	else if (binaryExp.getFirstExpression() instanceof VariableAST) {
	    leftVar = ((VariableAST) binaryExp.getFirstExpression()).getId().getType();
	    System.out.println("FirstVar");
	}
	else if (binaryExp.getFirstExpression() instanceof StringAST) {
	    leftVar = "String";
	    System.out.println("SecondStr");
	}
	else if (binaryExp.getFirstExpression() instanceof NumberAST) {
	    leftVar = "int";
	    System.out.println("SecondNum");
	}
	else if (binaryExp.getFirstExpression() instanceof BooleanAST) {
	    leftVar = "boolean";
	    System.out.println("SecondBoo");
	}
	// second expr

	if (binaryExp.getSecondExpression() instanceof AttributeCallAST) {
	    rightVar = ((AttributeCallAST) binaryExp.getSecondExpression()).getId().getType();
	    System.out.println("SecondAtt");
	}
	else if (binaryExp.getSecondExpression() instanceof VariableAST) {
	    rightVar = ((VariableAST) binaryExp.getSecondExpression()).getId().getType();
	    System.out.println("SecondVar");
	}
	else if (binaryExp.getSecondExpression() instanceof StringAST) {
	    rightVar = "String";
	    System.out.println("SecondStr");
	}
	else if (binaryExp.getSecondExpression() instanceof NumberAST) {
	    rightVar = "int";
	    System.out.println("SecondNum");
	}
	else if (binaryExp.getSecondExpression() instanceof BooleanAST) {
	    rightVar = "boolean";
	    System.out.println("SecondBoo");
	}

	if (leftVar.equalsIgnoreCase(rightVar)) {
	    System.out.println("equals");
	    System.out.println(leftVar + " == " + rightVar);
	}
	else {
	    System.out.println("not equals");
	    System.out.println(leftVar + " != " + rightVar);
	    errh.addError(new ParseError(ErrorClass.semantic, ErrorType.UncompatibleTypes, binaryExp.getRelation().getLineNo(), binaryExp.getRelation().getColumnNo()));
	}

	System.out.println("End");
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
	// TODO Auto-generated method stub

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
	// TODO Auto-generated method stub

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
	// TODO Auto-generated method stub

    }

    @Override
    public void visit(PredicateImplAST impl) {
	// TODO Auto-generated method stub

    }

    @Override
    public void visit(RelationAST relation) {
	// TODO Auto-generated method stub
	System.out.println("In relationAst");
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
	// TODO Auto-generated method stub

    }

    @Override
    public void visit(srcExprAST expr) {

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
	// TODO Auto-generated method stub

    }

}
