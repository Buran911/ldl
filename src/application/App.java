package application;

import java.sql.SQLException;

import generation.idtable.IdTable;
import generation.templateengine.Engine;
import generation.templateengine.QueryConstraints;
import generation.templateengine.QueryData;
import generation.walkers.strategys.BottomUpWalkingStrategy;
import generation.walkers.strategys.IdParsigStrategy;
import generation.walkers.walkers.IdConvertor;
import generation.walkers.walkers.IdTableFiller;
import generation.walkers.walkers.TemplateEqClassesFiller;
import generation.walkers.walkers.TemplateTypeFiller;
import parse.errhandler.ErrorHandler;
import parse.ldlsettingsparser.XMLParser;
import parse.parser.Parser;
import parse.syntaxtree.SyntaxTree;
import parse.util.Source;
import application.util.CmdLineParser;
import application.util.QueryMaker;
import application.util.YamlWriter;

public class App {
	private String[] args;
	private Source src;
	private SyntaxTree tree;
	private ErrorHandler errh;
	private Engine engine;
	private String conStr;
	private int columnCount;
	private QueryMaker queryMaker;
	
	public App(String[] args) {
		this.args = args;
	}

	public void readFiles() {
		System.out.println("Парсинг параметров командной строки.");
		CmdLineParser cmdLineParser = new CmdLineParser(args);
		
		if(!cmdLineParser.parse()){
			System.err.println("Не могу считать параметры командной строки");
			throw new RuntimeException();
		}
		System.out.println("Парсинг настроечного файла.");
		XMLParser parser = new XMLParser(cmdLineParser.getPropertyFile());
		parser.parse();
		conStr = parser.getConnectionString();
		
		src = new Source(cmdLineParser.getLdlFiles());
		errh = new ErrorHandler(src);
		
	}

	public void checkErrors() {
		Parser parser = new Parser(src, errh);
		parser.parse();
		System.out.println("Парсинг исходных файлов.");
		
		if(errh.hasErrors()){
			errh.printErrors();
			throw new RuntimeException();
		}
		
		tree = new SyntaxTree(parser.getTree());
	}
	


	public void generateEQ() {
		System.out.println("Обработка АСТ.");
		IdTable table = new IdTable();
		QueryConstraints qConstraints = new QueryConstraints();
		
		tree.accept( new IdTableFiller( new IdParsigStrategy(), table));
		tree.accept( new TemplateTypeFiller( new BottomUpWalkingStrategy()));
		tree.accept( new IdConvertor( new IdParsigStrategy(),table));
		tree.accept( new TemplateEqClassesFiller(new BottomUpWalkingStrategy(), qConstraints));
		
		columnCount = table.getDbIdCount();
		
		qConstraints.makeUnmodifiable();
		engine = new Engine(new QueryData(table), qConstraints);
		System.out.println("Генерация запроса(-ов).");
		engine.generate();
	}
	
	public void makeQuery() {
		System.out.println("Работа с БД.");
		queryMaker = new QueryMaker(conStr, engine.getQuery(), columnCount);
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
		YamlWriter yw = new YamlWriter(queryMaker.getQueryResults());
		yw.writeYAMLs();
	}


	
}
