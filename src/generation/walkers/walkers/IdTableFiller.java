package generation.walkers.walkers;

import generation.idtable.DataGenerator;
import generation.idtable.Database;
import generation.idtable.IdTable;
import generation.idtable.Identifier;
import generation.idtable.PyFunction;
import generation.idtable.Set;
import generation.languageconstants.Behaviour;
import generation.languageconstants.Messure;
import generation.languageconstants.ReservedWord;
import generation.languageconstants.Type;
import generation.walkers.TreeWalker;
import generation.walkers.WalkerStrategy;

import java.io.FileNotFoundException;
import java.util.Calendar;
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
import parse.util.FileReader;
import application.util.Halt;

/**
 * ������ ��������� ���������� �� ���������� ���������������.
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
    }

    @Override
    public void visit(BinaryExpressionAST binaryExp) {
    }

    @Override
    public void visit(BinaryOpAST binaryOp) {
    }

    @Override
    public void visit(BooleanAST bool) {
    }

    @Override
    public void visit(ConditionAST condition) {
    }

    @Override
    public void visit(ContextAST context) {
	contextName = context.getContextName().getName();
    }

    @Override
    public void visit(DescriptionAST description) {
    }

    @Override
    public void visit(EqClassAST eqClass) {
    }

    @Override
    public void visit(FormalParamsAST formalParams) {
    }

    @Override
    public void visit(FunctionalPartAST funcPart) {
    }

    @Override
    public void visit(IdentifierAST id) {
    }

    @Override
    public void visit(ldlAST ldl) {
    }

    @Override
    public void visit(NumberAST number) {
    }

    @Override
    public void visit(OperationCallAST operationCall) {
    }

    @Override
    public void visit(ParametresAST params) {
    }

    @Override
    public void visit(PathNameAST pathName) {
    }

    @Override
    public void visit(PredicateAST predicate) {
    }

    @Override
    public void visit(PredicateImplAST impl) {
    }

    @Override
    public void visit(RelationAST relation) {
    }

    @Override
    public void visit(SetAST set) {
    }

    @Override
    public void visit(SetOpAST setOp) {
    }

    @Override
    public void visit(SimpleNameAST simpleName) {
    }

    @Override
    public void visit(SourceAST src) {
    }

    @Override
    public void visit(srcBlockAST block) {
	Identifier id = table.getId(block.getIdentifier().getId(), contextName);

	// ���������� ��������
	srcExprAST expr = findReservedWord(ReservedWord.type, block.getSrcExprs().iterator());

	if (expr != null) {
	    id.setSrcType(Type.valueOf(expr.getSecondId().getId()));
	}

	// ��������� ������ �� ���������
	switch (id.getSrcType())
	    {
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
			}
			catch (FileNotFoundException e) {
			    throw new Halt();
			}
		    }
		}
		id.setSrcData(function);
		break;

	    case set:
		Set set = new Set();
		set.setName(id.getName());
		set.setAlias(id.getAlias());
		for (srcExprAST exp : block.getSrcExprs())
		    if (exp.getFirstId().getId().contentEquals(ReservedWord.value.word()))
			for (LiteralAST li : exp.getSet().getElements())
			    set.addElements(li);
		id.setSrcData(set);
		break;

	    case dategenerator:
		DataGenerator dataGenerator = new DataGenerator();

		for (srcExprAST exp : block.getSrcExprs()) {
		    if (exp.getFirstId().getId().contentEquals(ReservedWord.startdate.word())) {
			String[] array = ((StringAST) exp.getLiteral()).getString().split("/");
			Calendar date = Calendar.getInstance();
			date.clear();
			date.set(Integer.parseInt(array[2]), Integer.parseInt(array[1]) - 1, Integer.parseInt(array[0]));
			dataGenerator.setStartDate(date);
		    }
		    else if (exp.getFirstId().getId().contentEquals(ReservedWord.enddate.word())) {
			String[] array = ((StringAST) exp.getLiteral()).getString().split("/");
			Calendar date = Calendar.getInstance();
			date.clear();

			date.set(Integer.parseInt(array[2]), Integer.parseInt(array[1]) - 1, Integer.parseInt(array[0]));
			dataGenerator.setEndDate(date);
		    }
		    else if (exp.getFirstId().getId().contentEquals(ReservedWord.generationtype.word())) {
			String generationtype = exp.getSecondId().getId();
			if (generationtype.equals("sequence")) {
			    dataGenerator.setBehaviour(Behaviour.sequence);
			}
			else if (generationtype.equals("random")) {
			    dataGenerator.setBehaviour(Behaviour.random);
			}
		    }
		    else if (exp.getFirstId().getId().contentEquals(ReservedWord.step.word())) {
			Double step = ((NumberAST) exp.getLiteral()).getNumber();
			dataGenerator.setStep(step);
		    }
		    else if (exp.getFirstId().getId().contentEquals(ReservedWord.messure.word())) {
			String messure = ((StringAST) exp.getLiteral()).getData();
			if (messure.equals("day")) {
			    dataGenerator.setMessure(Messure.day);
			}
			else if (messure.equals("month")) {
			    dataGenerator.setMessure(Messure.month);
			}
			else if (messure.equals("year")) {
			    dataGenerator.setMessure(Messure.year);
			}
		    }
		}
		id.setSrcData(dataGenerator);
		break;
	    }
	// ������������� �������� _visible
	expr = findReservedWord(ReservedWord.visible, block.getSrcExprs().iterator());

	if (expr != null) {
	    id.setVisible(((BooleanAST) expr.getLiteral()).getBool());
	}
    }

    @Override
    public void visit(srcExprAST expr) {
    }

    @Override
    public void visit(StringAST string) {
    }

    @Override
    public void visit(TypeAST type) {
    }

    @Override
    public void visit(VariableAST var) {
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