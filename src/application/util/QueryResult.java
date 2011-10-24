package application.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class QueryResult {
	private HashMap<String, List<String>> queryResult;
	
	{
		queryResult = new HashMap<String, List<String>>();
	}
	
	public void addResult(String key, String value){
		List<String> curVal;
		
		if((curVal = queryResult.get(key)) != null){
			curVal.add(value);
		}
		else{
			curVal = new LinkedList<String>();
			curVal.add(value);
			queryResult.put(key, curVal);
		}
	}
	
	
	public List<String> get(String key){
		return queryResult.get(key);
	}


	public HashMap<String, List<String>> getQueryResult() {
		return queryResult;
	}
}
