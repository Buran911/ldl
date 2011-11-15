package generation.walkers.walkers;

import generation.idtable.IdTable;
import generation.idtable.Identifier;
import generation.idtable.Predicate;
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

public class IdNotDefinedChecker extends TreeWalker {
    private IdTable table;
    private String contextName;
    private ErrorHandler errh;

    public IdNotDefinedChecker(WalkerStrategy strategy, IdTable table, ErrorHandler errh) {
	super(strategy);
	this.table = table;
	this.errh = errh;
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
	contextName = context.getContextName().getName();
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
	Predicate pred = new Predicate();

	pred.setName(predicate.getOprCall().getIdentifier().getId());
	String namespace = null;
	if (predicate.getVariable() != null) {
	    namespace = table.getId(predicate.getVariable().getIdentifier().getId(), contextName)
		    .getType();
	}
	if (predicate.getAttrCall() != null) {
	    namespace = predicate.getAttrCall().getLastId(table, contextName).getNamespace();
	}
	if ((predicate.getAttrCall() == null) && (predicate.getVariable() == null)) {
	    namespace = contextName;
	}
	pred.setNamespace(namespace);

	if (table.getPredicate(pred.getName(), pred.getNamespace()) == null) {
	    errh.addError(new ParseError(ErrorClass.semantic, ErrorType.IdentifierUndefined,
		    predicate.getLineNo(), predicate.getColumnNo()));
	}
    }

    @Override
    public void visit(PredicateImplAST impl) {
	contextName = impl.getPathName().getContextName().getName();
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
	Identifier id = new Identifier();

	id.setNamespace(contextName);
	id.setName(block.getIdentifier().getId());

	if (table.getId(id.getName(), id.getNamespace()) == null) {
	    errh.addError(new ParseError(ErrorClass.semantic, ErrorType.IdentifierUndefined, block.getLineNo(),
		    block.getColumnNo()));
	}
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
	Identifier id = new Identifier();

	id.setNamespace(contextName);
	id.setName(var.getIdentifier().getId());

	if (table.getId(id.getName(), id.getNamespace()) == null) {
	    errh.addError(new ParseError(ErrorClass.semantic, ErrorType.IdentifierUndefined, var.getLineNo(),
		    var.getColumnNo()));
	}
    }
}
