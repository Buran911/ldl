package application.util;

import java.util.List;
import java.util.Map;
import java.util.Random;

import generation.db.Policy;
import generation.db.QueryResult;


public class ValueFilter implements Filterable {
    private Policy policy;
    private Random randomizer;
    
    public ValueFilter(Policy policy) {
	super();
	this.policy = policy;
	randomizer = new Random();
    }

    @Override
    public void filter(QueryResult queryResult) {
	for(Map.Entry<String, List<String>> entry : queryResult.getQueryResult().entrySet()){
	    List<String> values = entry.getValue();
	    switch (policy) {
		case first:
		    values.subList(1, values.size()).clear();
		    break;
		case last:
		    values.subList(0, values.size() - 1).clear();
		    break;
		case random:
		    values.add(values.get(randomizer.nextInt(values.size())));
		    values.subList(0, values.size() - 1).clear();
		    break;
	    }
	}
    }

}
