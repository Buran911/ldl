package application.util;

import generation.db.QueryResult;
import generation.idtable.IdTable;
import generation.idtable.Identifier;
import application.util.NumberGenerator;

/**
 * Фильтр, запускающий генератор номеров.
 * @author Buran911
 * */

public class NumGenFilter implements Filterable {
    private IdTable table;
    private NumberGenerator numgen;
    private boolean firstInit;
    
    public NumGenFilter(IdTable idTable) {
	super();
	this.table = idTable;
	firstInit = true;
    }

    
    @Override
    public void filter(QueryResult queryResult) {
	for (Identifier id : table.getIds()) {
	    if (id.getSrcData() instanceof NumberGenerator) {
		numgen = (NumberGenerator) id.getSrcData();
		queryResult.addResult(id.getAlias(), Integer.toString(numgen.generateNumber()));
	    }
	}
    }
    
}
