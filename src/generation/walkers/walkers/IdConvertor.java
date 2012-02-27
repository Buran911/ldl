package generation.walkers.walkers;

import generation.idtable.IdTable;
import generation.idtable.Identifier;
import generation.idtable.Predicate;
import generation.walkers.TreeWalker;
import generation.walkers.WalkerStrategy;
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
 * Волкер обходи узлы дерева имеющие идентификаторы и сопоставляет их идентифкаторам из таблицы идентификаторов.
 * @author hindu
 * */
public class IdConvertor extends TreeWalker {
    private IdTable table;
    private String contextName;
    private boolean attrCallVisited; // определяет, как будут обрабатываться
				     // вызовы атрибутов
    private boolean variableCanBeVisited;

    {
	attrCallVisited = false;
	variableCanBeVisited = true;
    }

    public IdConvertor(WalkerStrategy strategy, IdTable table) {
	super(strategy);
	this.table = table;
    }

    @Override
    public void visit(AttributeCallAST attrCall) {
	if (!attrCallVisited) {

	    // При обработке шаблнизатором первым будет обработан последний
	    // элемент
	    attrCall.setId(attrCall.getLastId(table, contextName));

	    attrCallVisited = true;
	    variableCanBeVisited = false;
	}

	if (attrCall.getAttrCall() == null) {
	    attrCallVisited = false;
	}
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
	String predicateName = predicate.getOprCall().getIdentifier().getData();

	if (predicate.getAttrCall() != null) {
	    Predicate pred = table.getPredicate(predicateName, predicate.getAttrCall().getId()
		    .getType());
	    predicate.setImpl((PredicateImplAST) pred.getImpl());
	}

	if (predicate.getVariable() != null) {
	    Predicate pred = table.getPredicate(predicateName, predicate.getVariable().getId()
		    .getType());
	    predicate.setImpl((PredicateImplAST) pred.getImpl());
	}

	if ((predicate.getAttrCall() == null) && (predicate.getVariable() == null)) {
	    Predicate pred = table.getPredicate(predicateName, contextName);
	    predicate.setImpl((PredicateImplAST) pred.getImpl());
	}
    }

    @Override
    public void visit(PredicateImplAST impl) {
	contextName = impl.getPathName().getContextName().getData();

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
	if (variableCanBeVisited) {
	    String idName = var.getIdentifier().getData();

	    Identifier id = table.getId(idName, contextName);
	    var.setId(id);
	}
	else {
	    // attrCall закончен
	    variableCanBeVisited = true;
	}
    }

}
