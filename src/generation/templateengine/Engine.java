package generation.templateengine;

import java.util.LinkedList;
import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class Engine {
	private QueryData queryData;
	private QueryConstraints queryConstraints;
	private LinkedList<String> query;
	
	{
		query = new LinkedList<String>();
	}

	public Engine(QueryData queryData, QueryConstraints queryConstraints) {
		this.queryData = queryData;
		this.queryConstraints = queryConstraints;
	}
	
	public List<String> getQuery() {
		return query;
	}

	public void generate() {
		String selFrom = generateSelectFrom();	
		while(queryConstraints.hasNext()){
			String where = generateWhere();
			
			// FIXME ������ �������
			String q = selFrom + where;
			query.add(q.replaceAll("'", ""));
		} 
		
	}

	private String generateSelectFrom(){
		STGroup group = new STGroupFile("generation/templates/select_from.stg");
		ST st = group.getInstanceOf("select");
		
		st.add("elem", queryData);
		String result = st.render();
		
		return result;
	}
	
	private String generateWhere() {
		STGroup group = new STGroupFile("generation/templates/where.stg");
		ST st = group.getInstanceOf("where");
		
		st.add("elem", queryConstraints.next());
		String result = st.render();
		
		return result;
	}

}
