package application;

import generation.db.DbConectionData;
import generation.db.Policy;
import generation.db.QueryMaker;
import generation.idtable.IdTable;
import generation.templateengine.Engine;
import generation.templateengine.QueryConstraints;
import generation.templateengine.QueryData;
import generation.walkers.strategys.BottomUpWalkingStrategy;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.FunctionalImplementedChecker;
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

import org.apache.log4j.Logger;

import parse.errhandler.ErrorHandler;
import parse.errhandler.ErrorType;
import parse.errhandler.ProgramState;
import parse.errhandler.RuntimeError;
import parse.errhandler.WalkerRunner;
import parse.ldlsettingsparser.XMLParser;
import parse.parser.Parser;
import parse.syntaxtree.SyntaxTree;
import parse.util.Source;
import application.util.CmdLineParser;
import application.util.Halt;
import application.util.YamlWriter;

/**
 * Класс интегрирует модули приложения и инкапсулирует всю работу приложения на
 * верхнем уровне.
 * 
 * @author hindu
 * */
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
    private Logger logger = Logger.getLogger(App.class);

    public App(String[] args) {
	this.args = args;
    }    
    // считывание и подготовка исходных файлов и считывание и парсинг
    // настроечного файла
    public void readFiles() {
	logger.info("Парсинг параметров командной строки.");
	CmdLineParser cmdLineParser = new CmdLineParser(args);

	if (!cmdLineParser.parse()) {
	    logger.error("Не могу считать параметры командной строки");
	    throw new Halt();
	}
	XMLParser parser = new XMLParser(cmdLineParser.getPropertyFile());
	logger.info("Парсинг настроечного файла.");
	logger.trace("property file:" + cmdLineParser.getPropertyFile());
	parser.parse();

	logger.trace("connection string: " + parser.getConnectionString());
	logger.trace("user: " + parser.getUser());
	logger.trace("password: " + parser.getPassword());
	logger.trace("policy: " + parser.getPolicy());

	connection = new DbConectionData();
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

    // парсинг исходных файлов и проверка синтаксических и семантических ошибок
    public void parseAndCheckErrors() {
	Parser parser = new Parser(src, errh);
	logger.info("Парсинг исходных файлов.");
	logger.info("Проверка синтаксических ошибок");

	parser.parse();
	
	logger.debug("program state : " + ProgramState.SyntaxChecked);
	errh.setProgramState(ProgramState.SyntaxChecked);
	
	tree = new SyntaxTree(parser.getTree());

	logger.info("Проверка семантических ошибок.");
	checkSemantics();
	
	logger.debug("program state : " + ProgramState.SemanticChecked);
	errh.setProgramState(ProgramState.SemanticChecked);
    }

    // Проверяет семантическую правильность исходного кода, проверка идет по
    // копиям объектов
    private void checkSemantics() {
	SyntaxTree treeSemantic = tree.clone();
	IdTable idTable = new IdTable();
	WalkerRunner runner = new WalkerRunner(errh, treeSemantic);

	runner.add(new TypeMismatchChecker(new IdParsigStrategy(), errh));
	runner.add(new FunctionalImplementedChecker(new BottomUpWalkingStrategy(), errh));
	runner.add(new IdRedefinedChecker(new IdParsigStrategy(), errh));
	runner.add(new IdTableMaker(new IdParsigStrategy(), idTable));
	runner.add(new IdTableFiller(new IdParsigStrategy(), idTable));
	runner.add(new IdNotDefinedChecker(new IdParsigStrategy(), idTable, errh));
	runner.add(new IdConvertor(new IdParsigStrategy(), idTable));
	runner.add(new PositionEstimater(new IdParsigStrategy()));


	runner.run();
	errh.printErrors();
    }

    // Предобработка AST и генерация по нему запросов.
    public void generateEQ() {
	logger.info("Обработка АСТ.");
	table = new IdTable();
	QueryConstraints qConstraints = new QueryConstraints();

	WalkerRunner runner = new WalkerRunner(errh, tree);

	runner.add(new IdTableMaker(new IdParsigStrategy(), table));
	runner.add(new PositionEstimater(new IdParsigStrategy()));
	runner.add(new IdTableFiller(new IdParsigStrategy(), table));
	runner.add(new TemplateTypeFiller());
	runner.add(new IdConvertor(new IdParsigStrategy(), table));
	runner.add(new TemplateEqClassesFiller(qConstraints));

	runner.run();

	engine = new Engine(new QueryData(table), qConstraints);
	logger.info("Генерация запроса(-ов).");
	engine.generate();
    }

    // Соединение с БД, запросы и обработка результата.
    public void makeQuery() {
	logger.info("Работа с БД.");
	queryMaker = new QueryMaker(connection, engine.getQuery());
	try {
	    queryMaker.makeQuerys();
	}
	catch (ClassNotFoundException e) {
	    RuntimeError re = new RuntimeError(ErrorType.DataBaseDriverNotFound, e,
		    ErrorType.DataBaseDriverNotFound.getDescription());
	    errh.addError(re);
	}
	catch (SQLException e) {
	    RuntimeError re = new RuntimeError(ErrorType.SQLError, e,
		    ErrorType.SQLError.getDescription());
	    errh.addError(re);
	}
    }

    // запись результатов в yaml файлы
    public void writeYAML() {
	logger.info("Запись YAML.");
	YamlWriter yw = new YamlWriter(queryMaker.getQueryResults(), policy, table);
	yw.writeYAMLs();
    }

}
