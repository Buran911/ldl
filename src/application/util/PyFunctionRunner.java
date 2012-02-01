package application.util;

import static generation.languageconstants.Type.function;
import generation.db.QueryResult;
import generation.idtable.IdTable;
import generation.idtable.Identifier;

import java.io.StringReader;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.log4j.Logger;

public class PyFunctionRunner {
    private IdTable table;
    private List<QueryResult> queryResults;
    private Logger logger = Logger.getLogger(PyFunctionRunner.class);


    public PyFunctionRunner(IdTable table, List<QueryResult> queryResults) {
	super();
	this.table = table;
	this.queryResults = queryResults;
    }
    
    public void run(){
	for(Identifier id : table.getIds()){
	    if(id.getSrcType() == function){
		for(QueryResult queryResult : queryResults){
		    if(!queryResult.isEmpty()){
			generation.idtable.PyFunction function = (generation.idtable.PyFunction)id.getSrcData();
		    	queryResult.addResult(id.getAlias(),
    			    evaluate(function.getMain(),
    				    function.getCode(),
    				    getParams(getParamsNames(function.getParams()), queryResult)));
		    }
		}
	    }
	}
    } 
    
    private String evaluate(String main, String code, Object[] params){
	ScriptEngine engine = new ScriptEngineManager().getEngineByName("python");
	Object result = null;
	try {
	    engine.eval(new StringReader(tabFilter(code)));
	    Invocable invokeEngine = (Invocable) engine;
	    result = invokeEngine.invokeFunction(main, params);
	} catch (ScriptException e) {
	    logger.error("Ошибка с выполнением скрипта.");
	    logger.trace( StackTrace.getStackTrace(e));
	    throw new Halt();
	} catch (NoSuchMethodException e) {
	    logger.error("Не найдена вызываемая функция в скрипте.");
	    logger.trace( StackTrace.getStackTrace(e));
	    throw new Halt();
	}
	
	return result.toString();
    }
    
    // FIXME переписать грамматику и удалить
    private String[] getParamsNames(String parametres){
	String[] names = parametres.split(",");
	for(int i = 0; i < names.length; i++){
	    names[i] = names[i].trim();
	}
	
	String[] paramsNames = new String[names.length];
	for( int i = 0; i < names.length; i++){
	    String[] id = names[i].split("::");
	    paramsNames[i] = table.getId(id[1], id[0]).getAlias();
	} 
	
	return paramsNames;
    }
    
    private String[] getParams(String[] paramsNames, QueryResult queryResult){
	String[] params = new String[paramsNames.length];
	
	for(int i = 0; i < paramsNames.length; i++){
	    params[i] = queryResult.get(paramsNames[i]).get(0);
	}
	
	return params;
    }
    
    private String tabFilter(String str){
	return str.replaceAll("\\t", "");
    }
}
