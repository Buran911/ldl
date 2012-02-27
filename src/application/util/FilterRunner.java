package application.util;

import generation.db.QueryResult;

import java.util.LinkedList;
import java.util.List;

public class FilterRunner {
    private List<QueryResult> queryResults;
    private List<Filterable> filters;
    
    public FilterRunner( List<QueryResult> list ){
	this.queryResults = list;
	filters = new LinkedList<Filterable>();
    }
    
    public void addFilter(Filterable filter){
	filters.add(filter);
    }

    public void run(){
	for(QueryResult queryResult : queryResults){
	    for( Filterable filter : filters){
		filter.filter(queryResult);
	    }
	}
    }
    
}
