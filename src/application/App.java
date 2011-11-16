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
import parse.ldlsettingsparser.XMLParser;
import parse.parser.Parser;
import parse.syntaxtree.SyntaxTree;
import parse.util.Source;
import application.util.CmdLineParser;
import application.util.DbConectionData;
import application.util.Halt;
import application.util.Policy;
import application.util.QueryMaker;
import application.util.StackTrace;
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
    private Logger logger = Logger.getLogger(App.class);

    public App(String[] args) {
	this.args = args;
    }

    public void readFiles() {
	logger.info("Парсинг параметров командной строки.");
	CmdLineParser cmdLineParser = new CmdLineParser(args);

	if (!cmdLineParser.parse()) {
	    System.err.println("Не могу считать параметры командной строки");
	    throw new RuntimeException();
	}
	XMLParser parser = new XMLParser(cmdLineParser.getPropertyFile());
	logger.info("Парсинг настроечного файла.");
	parser.parse();
	
	
	logger.trace("connection string: " + parser.getConnectionString());
	logger.trace("user: " + parser.getUser());
	logger.trace("password: " + parser.getPassword());
	logger.trace("policy: " + parser.getPolicy());
	
	connection = new DbConectionData();
	connection.setConnectionString(parser.getConnectionString());
	connection.setUser(parser.getUser());
	connection.setPassword(parser.getPassword());
	
	if(parser.getPolicy() == null){
	    // default policy
	    policy = Policy.first;
	}
	else{
	    policy = Policy.valueOf(parser.getPolicy());
	}
	

	src = new Source(cmdLineParser.getLdlFiles());
	errh = new ErrorHandler(src);

    }

    public void parseAndCheckErrors() {
	Parser parser = new Parser(src, errh);
	logger.info("Парсинг исходных файлов.");
	parser.parse();
	

	// Синтаксические ошибки
	logger.info("Проверка синтаксических ошибок");
	if (errh.hasErrors()) {
	    errh.printErrors();
	    throw new Halt();
	}

	tree = new SyntaxTree(parser.getTree());
	
	logger.info("Проверка семантических ошибок.");
	checkSemantics();
	
	// Семантические ошибки
	if (errh.hasErrors()) {
	    errh.printErrors();
	    throw new Halt();
	}
    }

    private void checkSemantics() {
	// TODO Сделать копию дерева
	SyntaxTree treeSemantic = (SyntaxTree) DeepCopy.getCopy(tree);
	IdTable idTable = new IdTable();
	
	tree.accept( new FunctionalImplementedChecker(new BottomUpWalkingStrategy(), errh));
	
	treeSemantic.accept(new PositionEstimater(new IdParsigStrategy()));
	
	treeSemantic.accept(new IdRedefinedChecker(new IdParsigStrategy(), errh));
	
	treeSemantic.accept(new IdTableMaker(new IdParsigStrategy(), idTable));
	treeSemantic.accept(new IdTableFiller(new IdParsigStrategy(), idTable));
	treeSemantic.accept(new IdConvertor(new IdParsigStrategy(), idTable));
	

	treeSemantic.accept(new IdNotDefinedChecker(new IdParsigStrategy(), idTable, errh));
	treeSemantic.accept(new TypeMismatchChecker(new IdParsigStrategy(), errh));
	
    }
    public void generateEQ() {
	logger.info("Обработка АСТ.");
	table = new IdTable();
	QueryConstraints qConstraints = new QueryConstraints();

	tree.accept(new IdTableMaker(new IdParsigStrategy(), table));
	tree.accept(new PositionEstimater(new IdParsigStrategy()));
	tree.accept(new IdTableFiller(new IdParsigStrategy(), table));
	tree.accept(new TemplateTypeFiller(new BottomUpWalkingStrategy()));
	tree.accept(new IdConvertor(new IdParsigStrategy(), table));
	tree.accept(new TemplateEqClassesFiller(new BottomUpWalkingStrategy(), qConstraints));

	qConstraints.makeUnmodifiable();
	engine = new Engine(new QueryData(table), qConstraints);
	logger.info("Генерация запроса(-ов).");
	engine.generate();
    }



    public void makeQuery() {
	logger.info("Работа с БД.");
	queryMaker = new QueryMaker(connection, engine.getQuery());
	try {
	    queryMaker.makeQuerys();
	} catch (ClassNotFoundException e) {
	    logger.error("Не найден драйвер БД.");
	    logger.trace( StackTrace.getStackTrace(e));
	    throw new Halt();
	} catch (SQLException e) {
	    logger.error("Ошибка в SQL запросе.");
	    logger.trace( StackTrace.getStackTrace(e));
	    throw new Halt();
	}

    }

    public void writeYAML() {
	logger.info("Запись YAML.");
	YamlWriter yw = new YamlWriter(queryMaker.getQueryResults(), policy, table);
	yw.writeYAMLs();
    }

}
