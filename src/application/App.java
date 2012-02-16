package application;

import generation.db.DatabaseConnectionData;
import generation.db.Policy;
import generation.db.QueryMaker;
import generation.idtable.IdTable;
import generation.templateengine.Engine;
import generation.templateengine.QueryConstraints;
import generation.templateengine.QueryData;
import generation.walkers.strategys.BottomUpWalkingStrategy;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.FunctionalImplementedChecker;
import generation.walkers.walkers.GroupFilter;
import generation.walkers.walkers.IdConvertor;
import generation.walkers.walkers.IdNotDefinedChecker;
import generation.walkers.walkers.IdRedefinedChecker;
import generation.walkers.walkers.IdTableFiller;
import generation.walkers.walkers.IdTableMaker;
import generation.walkers.walkers.PositionEstimater;
import generation.walkers.walkers.TemplateEqClassesFiller;
import generation.walkers.walkers.TemplateTypeFiller;
import generation.walkers.walkers.TypeMismatchChecker;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import parse.errhandler.ErrorHandler;
import parse.ldlsettingsparser.XMLParser;
import parse.parser.Parser;
import parse.syntaxtree.SyntaxTree;
import parse.util.Source;
import application.util.CmdLineParser;
import application.util.FilterRunner;
import application.util.Halt;
import application.util.PyFunctionRunner;
import application.util.StackTrace;
import application.util.ValueFilter;
import application.util.VisibleFilter;
import application.util.YamlWriter;

/**
 * ����� ����������� ������ ���������� � ������������� ��� ������ ���������� ��
 * ������� ������.
 * 
 * @author hindu
 * */
public class App {
    private String[] args;
    private Source src;
    private SyntaxTree tree;
    private ErrorHandler errh;
    private Engine engine;
    private DatabaseConnectionData connection;
    private Policy policy;
    private QueryMaker queryMaker;
    private IdTable table;
    private Logger logger = Logger.getLogger(App.class);
    private String pythonDir;
    private List<String> funcGroups;

    public App(String[] args) {
	this.args = args;
    }

    // ���������� � ���������� �������� ������ � ���������� � �������
    // ������������ �����
    public void readFiles() {
	logger.info("������� ���������� ��������� ������.");
	CmdLineParser cmdLineParser = new CmdLineParser(args);

	if (!cmdLineParser.parse()) {
	    logger.error("�� ���� ������� ��������� ��������� ������");
	    throw new Halt();
	}

	pythonDir = cmdLineParser.getPythonDir();
	XMLParser parser = new XMLParser(cmdLineParser.getPropertyFile());
	logger.info("������� ������������ �����.");
	logger.trace("property file:" + cmdLineParser.getPropertyFile());
	parser.parse();
	funcGroups = parser.getFunctionGroups();
	logger.trace("connection string: " + parser.getConnectionString());
	logger.trace("user: " + parser.getUser());
	logger.trace("password: " + parser.getPassword());
	logger.trace("policy: " + parser.getPolicy());

	connection = new DatabaseConnectionData();
	connection.setConnectionString(parser.getConnectionString());
	connection.setUser(parser.getUser());
	connection.setPassword(parser.getPassword());

	if (parser.getPolicy() == null) {
	    // default policy
	    policy = Policy.first;
	}
	else {
	    policy = Policy.valueOf(parser.getPolicy());
	}
	logger.trace("source file names: " + cmdLineParser.getLdlFiles());

	src = new Source(cmdLineParser.getLdlFiles());
	errh = new ErrorHandler(src);
    }

    // ������� �������� ������ � �������� �������������� � ������������� ������
    public void parseAndCheckErrors() {
	Parser parser = new Parser(src, errh);
	logger.info("������� �������� ������.");
	parser.setDebugModeOn();
	parser.parse();
	parser.getTree().printTree();
	// �������������� ������
	logger.info("�������� �������������� ������");
	if (errh.hasErrors()) {
	    errh.printErrors();
	    throw new Halt();
	}

	tree = new SyntaxTree(parser.getTree());

	logger.info("�������� ������������� ������.");
	checkSemantics();

	// ������������� ������
	if (errh.hasErrors()) {
	    errh.printErrors();
	    throw new Halt();
	}
    }

    // ��������� ������������� ������������ ��������� ����, �������� ���� ��
    // ������ ��������
    private void checkSemantics() {

	// TODO ������� ����� ������
	SyntaxTree treeSemantic = (SyntaxTree) tree.clone();
	IdTable idTable = new IdTable();

	treeSemantic.accept(new FunctionalImplementedChecker(new BottomUpWalkingStrategy(), errh));
	treeSemantic.accept(new PositionEstimater(new IdParsigStrategy()));
	treeSemantic.accept(new IdRedefinedChecker(new IdParsigStrategy(), errh));
	treeSemantic.accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	treeSemantic.accept(new IdTableFiller(new IdParsigStrategy(), idTable, pythonDir));
	treeSemantic.accept(new IdConvertor(new IdParsigStrategy(), idTable));
	treeSemantic.accept(new GroupFilter(new IdParsigStrategy(), funcGroups));
	treeSemantic.accept(new IdNotDefinedChecker(new IdParsigStrategy(), idTable, errh));
	treeSemantic.accept(new TypeMismatchChecker(new IdParsigStrategy(), errh));
    }

    // ������������� AST � ��������� �� ���� ��������.
    public void generateEQ() {
	logger.info("��������� ���.");
	table = new IdTable();
	QueryConstraints qConstraints = new QueryConstraints();

	tree.accept(new IdTableMaker(new IdParsigStrategy(), table));
	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdTableFiller(new IdParsigStrategy(), table, pythonDir));
	tree.accept(new TemplateTypeFiller());
	tree.accept(new IdConvertor(new IdParsigStrategy(), table));
	tree.accept(new TemplateEqClassesFiller(qConstraints));

	engine = new Engine(new QueryData(table), qConstraints);
	logger.info("��������� �������(-��).");
	engine.generate();
    }

    // ���������� � ��, ������� � ��������� ����������.
    public void makeQuery() {
	logger.info("������ � ��.");
	queryMaker = new QueryMaker(connection, engine.getQuery());
	try {
	    queryMaker.makeQuerys();
	}
	catch (ClassNotFoundException e) {
	    logger.error("�� ������ ������� ��.");
	    logger.trace(StackTrace.getStackTrace(e));
	    throw new Halt();
	}
	catch (SQLException e) {
	    logger.error("������ � SQL �������.");
	    logger.trace(StackTrace.getStackTrace(e));
	    throw new Halt();
	}

    }

    // ���������� �����������, ���������� �������
    public void postProcess() {
	logger.info("���������� ����������� � �������������.");
	FilterRunner filterRunner = new FilterRunner(queryMaker.getQueryResults());

	filterRunner.addFilter(new VisibleFilter(table));
	filterRunner.addFilter(new ValueFilter(policy));

	filterRunner.run();

	PyFunctionRunner pyRunner = new PyFunctionRunner(table, queryMaker.getQueryResults());
	pyRunner.run();
    }

    // ������ ����������� � yaml �����
    public void writeYAML() {
	logger.info("������ YAML.");
	YamlWriter yw = new YamlWriter(queryMaker.getQueryResults());
	yw.writeYAMLs();
    }

}
