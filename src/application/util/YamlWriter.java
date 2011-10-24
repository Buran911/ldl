package application.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class YamlWriter {
	private List<QueryResult> queryResults;
	private String dir = "out/";
	private Policy policy;

	public YamlWriter(List<QueryResult> queryResults, Policy returnPolicy) {
		super();
		this.queryResults = queryResults;
		this.policy = returnPolicy;
	}
	
	public void writeYAMLs(){
		int count = 0;
		
		STGroup group = new STGroupFile("generation/templates/yaml.stg");
		
		
		for(QueryResult queryResult : queryResults){
			ST st = group.getInstanceOf("yaml");
			st.add("elem", queryResult.get2valListView(policy));
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
}
