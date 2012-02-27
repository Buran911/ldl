package application.util;

import generation.db.QueryResult;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * ����� ���������� �������� ������ ���������� � ��� � ������� yaml, �������� ��������.
 * �������� ������ ����������� � ������� �������� StringTemplate.
 * @author hindu
 * */
public class YamlWriter {
    private List<QueryResult> queryResults;
    private String dir = "out/";
    private Logger logger = Logger.getLogger(YamlWriter.class);
    
    public YamlWriter(List<QueryResult> queryResults) {
	super();
	this.queryResults = queryResults;
    }

    public void writeYAMLs() {
	Integer count = 0;

	for (QueryResult queryResult : queryResults) {
	    Properties prop = queryResult.getProperties();
	    logger.info("Count: " + count + " Prop: " + prop.toString());
	    try {
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(dir
			    + count.toString() + ".yaml"), "windows-1251"));
		prop.store(printWriter, null);
		/*prop.store(new PrintWriter(new OutputStreamWriter(new FileOutputStream(dir
		    + count.toString() + ".yaml"), "windows-1251")),
		    null); */
	    } catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    count++;
	}
    }
}
