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

/**
 * Класс пребразует выходные данные генератора в ОПД в формате yaml, фильтруя ненужное.
 * Выходные данные формируются с помощью габлонов StringTemplate.
 * @author hindu
 * */
public class YamlWriter {
    private List<QueryResult> queryResults;
    private String dir = "out/";

    public YamlWriter(List<QueryResult> queryResults) {
	super();
	this.queryResults = queryResults;
    }

    public void writeYAMLs() {
	Integer count = 0;

	for (QueryResult queryResult : queryResults) {
	    Properties prop = queryResult.getProperties();
	    try {
		prop.store(new PrintWriter(new OutputStreamWriter(new FileOutputStream(dir
		    + count.toString() + ".yaml"), "windows-1251")),
		    null);
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
