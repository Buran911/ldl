package generation.templateengine;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * Шаблонизатор. Класс генерирует запросы по исходным данным: SyntaxTree,
 * EqualityClass и SelectFrom.
 * 
 * @author hindu
 * */
public class Engine {
    private QueryData queryData;
    private QueryConstraints queryConstraints;
    private LinkedList<String> query;
    private Logger logger = Logger.getLogger(Engine.class);

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
	if(queryData.getSet().size() != 0)
	    selFrom += generateJoinExpression();
	// FIXME коряво

	if (!queryConstraints.hasNext()) {
	    if (queryConstraints.hasConstConstraints()) {
		String where = generateWhere();
    	    
		String q = selFrom + where;
		while (q.contains("\r\n\r\n")) {
		    q = q.replaceAll("\r\n\r\n", "\r\n");
		}

		logger.trace("Query:\n" + q);
		query.add(q);
	    }
	    else {
		logger.trace("Query:\n" + selFrom);
		query.add(selFrom);
	    }
	}
	while (queryConstraints.hasNext()) {
	    String where = generateWhere();

	    String q = selFrom + where;
	    while (q.contains("\r\n\r\n")) {
		q = q.replaceAll("\r\n\r\n", "\r\n");
	    }

	    logger.trace("Query:\n" + q);
	    query.add(q);
	}
    }

    private String generateSelectFrom() {
	STGroup group = new STGroupFile("generation/templates/select_from.stg");
	ST st = group.getInstanceOf("query");

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

    private String generateJoinExpression() {
	STGroup group = new STGroupFile("generation/templates/query.stg");
	ST st = group.getInstanceOf("set");

	st.add("elems", queryData.getSet());
	String result = st.render();

	return result;
    }
}
