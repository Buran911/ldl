package generation.walkers.walkers;

import generation.idtable.Database;
import generation.idtable.IdTable;
import generation.idtable.Identifier;
import generation.idtable.PyFunction;
import generation.languageconstants.ReservedWord;
import generation.languageconstants.Type;
import generation.walkers.TreeWalker;
import generation.walkers.WalkerStrategy;

import java.io.FileNotFoundException;
import java.util.Iterator;

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
import parse.util.FileReader;
import application.util.Halt;
import application.util.NumberGenerator;

/**
 * ¬олкер заполн€ет информацию об источниках идентификаторов.
 * 
 * @author hindu
 * */
public class IdTableFiller extends TreeWalker {
    private IdTable table;
    private String contextName;
    private String pythonDir;
    
    public IdTableFiller(WalkerStrategy strategy, IdTable table, String pythonDir) {
	super(strategy);
	this.table = table;
	this.pythonDir = pythonDir;
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
	Identifier id = table.getId(block.getIdentifier().getId(), contextName);

	// определ€ем источник
	srcExprAST expr = findReservedWord(ReservedWord.type, block.getSrcExprs().iterator());

	if (expr != null) {
	    id.setSrcType(Type.valueOf(expr.getSecondId().getId()));
	}

	// заполн€ем данные об источнике
	switch (id.getSrcType()) {
	case db:
	    Database db = new Database(); 

	    for (srcExprAST exp : block.getSrcExprs()) {
		if (exp.getFirstId().getId().contentEquals(ReservedWord.table.word())) {
		    db.setTable(((StringAST) exp.getLiteral()).getString());
		}

		if (exp.getFirstId().getId().contentEquals(ReservedWord.column.word())) {
		    db.setColumn(((StringAST) exp.getLiteral()).getString());
		}
	    }

	    id.setSrcData(db);
	    break;

	case function:
	    PyFunction function = new PyFunction();

	    for (srcExprAST exp : block.getSrcExprs()) {
		if (exp.getFirstId().getId().contentEquals(ReservedWord.main.word())) {
		    function.setMain(((StringAST) exp.getLiteral()).getString());
		}
		if (exp.getFirstId().getId().contentEquals(ReservedWord.params.word())) {
		    for (IdentifierAST iden : exp.getSecondIds()) {
			String ali = table.getId(iden.getId(), contextName).getAlias();
			function.addParam(ali);
		    }
		}
		if (exp.getFirstId().getId().contentEquals(ReservedWord.code.word())) {
		    function.setCode(((StringAST) exp.getLiteral()).getString());
		}
		if (exp.getFirstId().getId().contentEquals(ReservedWord.codepath.word())) {
		    try {
			String path = getPath(((StringAST) exp.getLiteral()).getString());
			String content = FileReader.readFile(path);
			function.setCode(content);
		    } catch (FileNotFoundException e) {
			throw new Halt();
		    }
		}
	    }

	    id.setSrcData(function);
	    break;
	    
	case NumberGenerator:
	    NumberGenerator numbGen = new NumberGenerator();	//SdcData - test
	    for (srcExprAST exp : block.getSrcExprs()){
		if (exp.getFirstId().getId().contentEquals(ReservedWord.generationType.word())){
		    System.out.println("   " + (exp.getFirstId()).getData() + " : " + (exp.getSecondId()).getData() );
		    String bufstr = exp.getSecondId().getData();		//TEST!!!
		    numbGen.setGenType(bufstr);	//SdcData - test
		}
		if (exp.getFirstId().getId().contentEquals(ReservedWord.beginVal.word())){
		    System.out.println("   " + (exp.getFirstId()).getData() + " : " + (((NumberAST)exp.getLiteral()).getNumber().toString()));    //getSecondId()).getData() );
		    double val = ((NumberAST)exp.getLiteral()).getNumber();
		    numbGen.setBeginVal((int)val);	//SdcData - test
		}
		if (exp.getFirstId().getId().contentEquals(ReservedWord.period.word())){
		    System.out.println("   " + (exp.getFirstId()).getData() + " : " + (((NumberAST)exp.getLiteral()).getNumber().toString()));    //getSecondId()).getData() );
		    double val = ((NumberAST)exp.getLiteral()).getNumber();
		    numbGen.setPeriod((int)val);	//SdcData - test
		}
		if (exp.getFirstId().getId().contentEquals(ReservedWord.endVal.word())){
		    System.out.println("   " + (exp.getFirstId()).getData() + " : " + (((NumberAST)exp.getLiteral()).getNumber().toString()));    //getSecondId()).getData() );
		    double val = ((NumberAST)exp.getLiteral()).getNumber();
		    numbGen.setEndVal((int)val);	//SdcData - test
		}
	    }
	    id.setSrcData(numbGen);	//SdcData - test
	    break;
	}

	// устанавливаем параметр _visible
	expr = findReservedWord(ReservedWord.visible, block.getSrcExprs().iterator());

	if (expr != null) {
	    id.setVisible(((BooleanAST) expr.getLiteral()).getBool());
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
	// Empty

    }

    private srcExprAST findReservedWord(ReservedWord word, Iterator<srcExprAST> itExpr) {
	srcExprAST expr = null;
	while (itExpr.hasNext() && !((expr = itExpr.next()).getFirstId().getId().contentEquals(word.word())))
	    ;

	if (expr.getFirstId().getId().contentEquals(word.word())) {

	    return expr;
	}
	return null;
    }

    private String getPath(String codepath) {
	String path;
	if (pythonDir == null) {
	    path = codepath;
	}
	else {
	    if (!pythonDir.endsWith("/")) {
		pythonDir += "/";
	    }
	    path = pythonDir + codepath;
	}
	return path;
    }
}
