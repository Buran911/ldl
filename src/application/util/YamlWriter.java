package application.util;

import generation.idtable.IdTable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class YamlWriter {
	private List<QueryResult> queryResults;
	private String dir = "out/";
	private Policy policy;
	private IdTable table;
	private Random randomizer;
	
	public YamlWriter(List<QueryResult> queryResults, Policy returnPolicy, IdTable table) {
		super();
		this.queryResults = queryResults;
		this.policy = returnPolicy;
		this.table = table;
		randomizer = new Random();
	}
	
	public void writeYAMLs(){
		int count = 0;
		
		STGroup group = new STGroupFile("generation/templates/yaml.stg");
		
		
		for(QueryResult queryResult : queryResults){
			ST st = group.getInstanceOf("yaml");
			st.add("elem", filter(queryResult));
			String result = st.render();
			
			writeFile(result, count);
			count++;
		}
	}
	
	private void writeFile(String text, Integer index){
		PrintWriter writer = null;
			   
		try {
			writer = new PrintWriter(
				             new OutputStreamWriter(
				             new FileOutputStream( dir + index.toString() + ".yaml" ), "windows-1251"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writer.write(text);
		writer.close();
	}
	
	private List<List<String>> filter(QueryResult queryResult){
		List<List<String>> patternData = new LinkedList<List<String>>();
		
		for(String key  : queryResult.getQueryResult().keySet()){
			// ���������, ����� �� �����������?
			if(table.getId(key).isVisible()){
				List<String> values = queryResult.get(key);
				List<String> pairs = new LinkedList<String>();
				pairs.add(key);
				switch (policy){
					case first:
						pairs.add(values.get(0));
						break;
					case last:
						pairs.add( values.get( values.size() - 1));
						break;
					case random:
						pairs.add( values.get( randomizer.nextInt( values.size() )));
						break;
				}	
				patternData.add(pairs);
			}
		}
		
		return patternData;
	}
}
