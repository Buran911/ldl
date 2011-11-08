package generation.walkers.walkers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
import generation.idtable.IdTable;
import generation.idtable.Identifier;
import generation.idtable.Predicate;
import generation.walkers.TreeWalker;
import generation.walkers.WalkerStrategy;

public class NoDefinition extends TreeWalker {

    private IdTable table;
    private String contextName;
    private ErrorHandler errh;
    private Predicate predicate;

    public NoDefinition(WalkerStrategy strategy, IdTable table, ErrorHandler errh) {
	super(strategy);
	this.table = table;
	this.errh = errh;
    }

    @Override
    public void visit(AttributeCallAST attrCall) {
	// TODO Auto-generated method stub

    }

    @Override
    public void visit(BinaryExpressionAST binaryExp) {
	// TODO Auto-generated method stub

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
	contextName = context.getContextName().getName();
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
	Predicate pred = new Predicate();

	pred.setName(predicate.getOprCall().getIdentifier().getId());
	String namespace = null;
	if (predicate.getVariable() != null) {
	    namespace = table.getId(predicate.getVariable().getIdentifier().getId(), contextName).getType();
	}
	if (predicate.getAttrCall() != null) {

	    namespace = predicate.getAttrCall().getLastId(table, contextName).getNamespace();

	}
	if ((predicate.getAttrCall() == null) && (predicate.getVariable() == null)) {
	    namespace = contextName;
	}
	pred.setNamespace(namespace);

	if (table.getPredicate(pred.getName(), pred.getNamespace()) == null) {
	    errh.addError(new ParseError(ErrorClass.semantic, ErrorType.IdentifierUndefined, predicate.getLineNo(), predicate.getColumnNo()));
	}
    }

    @Override
    public void visit(PredicateImplAST impl) {

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
	Identifier id = new Identifier();

	id.setNamespace(contextName);
	id.setName(block.getIdentifier().getId());

	if (table.getId(id.getName(), id.getNamespace()) == null) {
	    errh.addError(new ParseError(ErrorClass.semantic, ErrorType.IdentifierUndefined, block.getLineNo(), block.getColumnNo()));
	}
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
	Identifier id = new Identifier();

	id.setNamespace(contextName);
	id.setName(var.getIdentifier().getId());

	if (table.getId(id.getName(), id.getNamespace()) == null) {
	    errh.addError(new ParseError(ErrorClass.semantic, ErrorType.IdentifierUndefined, var.getLineNo(), var.getColumnNo()));
	}
    }
}
