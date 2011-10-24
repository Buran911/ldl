package application.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class QueryResult {
	private HashMap<String, List<String>> queryResult;
	private Random randomizer;
	
	{
		queryResult = new HashMap<String, List<String>>();
		randomizer = new Random();
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
	
	public List<List<String>> get2valListView(Policy policy){
		List<List<String>> vals = new LinkedList<List<String>>();
		
		for(String key  : queryResult.keySet()){
			List<String> mix = new LinkedList<String>();
			
			mix.add(key);
			List<String> values = queryResult.get(key);
			switch (policy){
				case first:
					mix.add(values.get(0));
					break;
				case last:
					mix.add(values.get( values.size() - 1));
					break;
				case random:
					mix.add(values.get( randomizer.nextInt( values.size() )));
					break;
			}	
			
			vals.add(mix);
		}
		
		return vals;
	}
	
	public List<List<String>> getListView(){
		List<List<String>> vals = new LinkedList<List<String>>();
		
		for(String key  : queryResult.keySet()){
			List<String> mix = new LinkedList<String>();
			
			mix.add(key);
			mix.addAll(queryResult.get(key));
			
			vals.add(mix);
		}
		
		return vals;
	}
}
