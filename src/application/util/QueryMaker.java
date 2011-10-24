package application.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class QueryMaker {
	private List<String> querys;
	private List<QueryResult> queryResults;
	private String connectionString;
	private int columnCount;
	
	{
		queryResults = new LinkedList<QueryResult>();
	}
	
	public QueryMaker(String conStr, List<String> querys, int columnCount) {
		this.querys = querys;
		connectionString = conStr;
		this.columnCount = columnCount;
	}
	
	public void makeQuerys() throws ClassNotFoundException, SQLException{
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager
				.getConnection(connectionString);
		Statement stat = conn.createStatement();
		
		
		for(String query : querys){
			queryResults.add(exec(query,stat));
		}
		
		conn.close();
	}
	
	public List<QueryResult> getQueryResults() {
		return queryResults;
	}

	private QueryResult exec(String query, Statement stat) throws SQLException{
		QueryResult queryResult = new QueryResult();
		
		ResultSet rs = stat.executeQuery(query);
		
		int i = 0;
		ResultSetMetaData meta = rs.getMetaData();
		
		while (rs.next()) {
			for (int j = 1; j <= columnCount; j++) {
				// XXX слабое место
				String columnLabel = meta.getColumnLabel(j);
				queryResult.addResult(columnLabel, rs.getString(j));
			}
			i++;
		}
		
		rs.close();
		
		return queryResult;
	}
}
