package application;

import generation.idtable.IdTable;
import generation.templateengine.Engine;
import generation.templateengine.QueryConstraints;
import generation.templateengine.QueryData;
import generation.util.DeepCopy;
import generation.walkers.strategys.BottomUpWalkingStrategy;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.FunctionalImplementedChecker;
import generation.walkers.walkers.IdConvertor;
import generation.walkers.walkers.IdNotDefinedChecker;
import generation.walkers.walkers.IdTableFiller;
import generation.walkers.walkers.IdTableMaker;
import generation.walkers.walkers.PositionEstimater;
import generation.walkers.walkers.IdRedefinedChecker;
import generation.walkers.walkers.TemplateEqClassesFiller;
import generation.walkers.walkers.TemplateTypeFiller;
import generation.walkers.walkers.TypeMismatchChecker;

import java.sql.SQLException;

import parse.errhandler.ErrorHandler;
import parse.ldlsettingsparser.XMLParser;
import parse.parser.Parser;
import parse.syntaxtree.SyntaxTree;
import parse.util.Source;
import application.util.CmdLineParser;
import application.util.DbConectionData;
import application.util.Policy;
import application.util.QueryMaker;
import application.util.YamlWriter;

public class App {
    private String[] args;
    private Source src;
    private SyntaxTree tree;
    private ErrorHandler errh;
    private Engine engine;
    private DbConectionData connection;
    private Policy policy;
    private QueryMaker queryMaker;
    private IdTable table;

    public App(String[] args) {
	this.args = args;
    }

    public void readFiles() {
	System.out.println("Парсинг параметров командной строки.");
	CmdLineParser cmdLineParser = new CmdLineParser(args);

	if (!cmdLineParser.parse()) {
	    System.err.println("Не могу считать параметры командной строки");
	    throw new RuntimeException();
	}
	System.out.println("Парсинг настроечного файла.");
	XMLParser parser = new XMLParser(cmdLineParser.getPropertyFile());
	parser.parse();
	connection = new DbConectionData();
	connection.setConnectionString(parser.getConnectionString());
	connection.setUser(parser.getUser());
	connection.setPassword(parser.getPassword());
	policy = Policy.valueOf(parser.getPolicy());

	src = new Source(cmdLineParser.getLdlFiles());
	errh = new ErrorHandler(src);

    }

    public void checkErrors() {
	Parser parser = new Parser(src, errh);
	parser.parse();
	System.out.println("Парсинг исходных файлов.");

	if (errh.hasErrors()) {
	    errh.printErrors();
	    throw new RuntimeException();
	}

	tree = new SyntaxTree(parser.getTree());

	checkSemantics();
    }

    public void generateEQ() {
	System.out.println("Обработка АСТ.");
	table = new IdTable();
	QueryConstraints qConstraints = new QueryConstraints();
	
	tree.accept(new IdTableMaker(new IdParsigStrategy(), table));
	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdTableFiller(new IdParsigStrategy(), table));
	tree.accept(new TemplateTypeFiller(new BottomUpWalkingStrategy()));
	tree.accept(new IdConvertor(new IdParsigStrategy(), table));
	tree.accept(new TemplateEqClassesFiller(new BottomUpWalkingStrategy(), qConstraints));

	// Семантика
	tree.accept(new FunctionalImplementedChecker(new BottomUpWalkingStrategy(), errh));

	qConstraints.makeUnmodifiable();
	engine = new Engine(new QueryData(table), qConstraints);
	System.out.println("Генерация запроса(-ов).");
	engine.generate();
    }

    private void checkSemantics() {

	// TODO Сделать копию дерева
	SyntaxTree treeSemantic = (SyntaxTree)tree.clone();
	IdTable idTable = new IdTable();
	treeSemantic.accept(new PositionEstimater(new IdParsigStrategy()));

	treeSemantic.accept(new IdRedefinedChecker(new IdParsigStrategy(), errh));

	treeSemantic.accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	treeSemantic.accept(new IdConvertor(new IdParsigStrategy(), idTable));

	treeSemantic.accept(new IdNotDefinedChecker(new IdParsigStrategy(), idTable, errh));
	treeSemantic.accept(new TypeMismatchChecker(new IdParsigStrategy(), errh));

    }

    public void makeQuery() {
	System.out.println("Работа с БД.");
	queryMaker = new QueryMaker(connection, engine.getQuery());
	try {
	    queryMaker.makeQuerys();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	    throw new RuntimeException();
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new RuntimeException();
	}

    }

    public void writeYAML() {
	System.out.println("Запись YAML.");
	YamlWriter yw = new YamlWriter(queryMaker.getQueryResults(), policy, table);
	yw.writeYAMLs();
    }

}
