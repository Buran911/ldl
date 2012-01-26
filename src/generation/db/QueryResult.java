package generation.db;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Класс содержит результат запроса к БД.
 * Данные хранятся в хэшмапе. Формат: <имя_столбца> : <сипсок значений>.
 * @author hindu
 * */
public class QueryResult {
    private HashMap<String, List<String>> queryResult;

    {
	queryResult = new HashMap<String, List<String>>();
    }

    public void addResult(String key, String value) {
	List<String> curVal;

	if ((curVal = queryResult.get(key)) != null) {
	    curVal.add(value);
	}
	else {
	    curVal = new LinkedList<String>();
	    curVal.add(value);
	    queryResult.put(key, curVal);
	}
    }

    public List<String> get(String key) {
	return queryResult.get(key);
    }

    public HashMap<String, List<String>> getQueryResult() {
	return queryResult;
    }
    
    public Properties getProperties(){
	Properties property = new Properties();
	for(Map.Entry<String, List<String>> entry : queryResult.entrySet()){
	    property.put(entry.getKey(), entry.getValue().get(0));
	}
	
	return property;
    }
}
