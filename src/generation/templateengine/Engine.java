package generation.templateengine;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class Engine {
	private QueryData queryData;
	private QueryConstraints queryConstraints;
	private String query;
	
	{
		query = "";
	}

	public Engine(QueryData queryData, QueryConstraints queryConstraints) {
		this.queryData = queryData;
		this.queryConstraints = queryConstraints;
	}
	
	public String getQuery() {
		// FIXME убрать костыль
		return query.replaceAll("'", "");
	}

	public void generate() {
		generateSelectFrom();	
		generateWhere();
	}

	public void generateSelectFrom(){
		STGroup group = new STGroupFile("generation/templates/select_from.stg");
		ST st = group.getInstanceOf("select");
		
		st.add("elem", queryData);
		
		String result = st.render();
		query += result;
	}
	
	private void generateWhere() {
		STGroup group = new STGroupFile("generation/templates/where.stg");
		ST st = group.getInstanceOf("where");
		
		st.add("elem", queryConstraints);
		
		String result = st.render();
		query += result;
	}

}
