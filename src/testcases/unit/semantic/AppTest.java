package testcases.unit.semantic;

import generation.idtable.IdTable;
import generation.templateengine.Engine;
import parse.errhandler.ErrorHandler;
import parse.ldlsettingsparser.XMLParser;
import parse.parser.Parser;
import parse.syntaxtree.SyntaxTree;
import parse.util.Source;
import application.util.CmdLineParser;
import application.util.DbConectionData;
import application.util.Policy;
import application.util.QueryMaker;

public class AppTest {
	private String[] args;
	private Source src;
	private SyntaxTree tree;
	private ErrorHandler errh;
	private DbConectionData connection;
	
	public AppTest(String[] args) {
		this.args = args;
	}

	public void readFiles() {
		System.out.println("������� ���������� ��������� ������.");
		CmdLineParser cmdLineParser = new CmdLineParser(args);
		
		if(!cmdLineParser.parse()){
			System.err.println("�� ���� ������� ��������� ��������� ������");
			throw new RuntimeException();
		}
		System.out.println("������� ������������ �����.");
		XMLParser parser = new XMLParser(cmdLineParser.getPropertyFile());
		parser.parse();
		connection = new DbConectionData();
		connection.setConnectionString(parser.getConnectionString());
		connection.setUser(parser.getUser());
		connection.setPassword(parser.getPassword());
		
		src = new Source(cmdLineParser.getLdlFiles());
		errh = new ErrorHandler(src);
		
	}

	public void checkErrors() {
		Parser parser = new Parser(src, errh);
		parser.parse();
		System.out.println("������� �������� ������.");
		
		if(errh.hasErrors()){
			errh.printErrors();
			throw new RuntimeException();
		}
		
		tree = new SyntaxTree(parser.getTree());
	}
	
	public SyntaxTree getTree(){
		return tree;
	}
	
	public ErrorHandler getErrh() {
		return errh;
	}


	
}
