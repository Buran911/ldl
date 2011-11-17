package generation.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * ����� ������������ ������� � �� � ��������� ���������� �������� � @see QueryResult. 
 * @author hindu
 * */
public class QueryMaker {
    private List<String> querys;
    private List<QueryResult> queryResults;
    private DbConectionData connection;
    private DBMS dbms;

    {
	queryResults = new LinkedList<QueryResult>();
    }

    public QueryMaker(DbConectionData connection, List<String> querys) {
	this.querys = querys;
	this.connection = connection;
    }

    public void makeQuerys() throws ClassNotFoundException, SQLException {
	estimateDbms();

	switch (dbms) {
	case sqlite:
	    Class.forName("org.sqlite.JDBC");
	    break;
	case oracle:
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    break;
	}

	Connection conn = DriverManager.getConnection(connection.getConnectionString(),
		connection.getUser(), connection.getPassword());
	Statement stat = conn.createStatement();

	for (String query : querys) {
	    queryResults.add(exec(query, stat));
	}

	conn.close();
    }

    public List<QueryResult> getQueryResults() {
	return queryResults;
    }

    private QueryResult exec(String query, Statement stat) throws SQLException {
	QueryResult queryResult = new QueryResult();

	ResultSet rs = stat.executeQuery(query);
	ResultSetMetaData meta = rs.getMetaData();

	while (rs.next()) {
	    for (int j = 1; j <= meta.getColumnCount(); j++) {
		String columnLabel = meta.getColumnLabel(j);
		queryResult.addResult(columnLabel, rs.getString(j));
	    }
	}

	rs.close();

	return queryResult;
    }

    private void estimateDbms() {
	String dbmsString = connection.getConnectionString().split(":")[1];
	dbms = DBMS.valueOf(dbmsString);
    }
}
