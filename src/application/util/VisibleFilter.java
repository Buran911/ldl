package application.util;

import generation.db.QueryResult;
import generation.idtable.IdTable;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VisibleFilter implements Filterable {
    private IdTable table;
    
    public VisibleFilter(IdTable table) {
	super();
	this.table = table;
    }

    @Override
    public void filter(QueryResult queryResult) {
	Iterator<Map.Entry<String, List<String>>> itr = queryResult.getQueryResult().entrySet().iterator();
	
	while(itr.hasNext()){
	    if(!table.getId(itr.next().getKey()).isVisible()){
		itr.remove();
	    }
	}
    }

}
