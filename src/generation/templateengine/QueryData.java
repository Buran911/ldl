package generation.templateengine;

import generation.idtable.Database;
import generation.idtable.IdTable;
import generation.idtable.Identifier;
import generation.idtable.Set;
import generation.languageconstants.Type;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс содержит в себе информацию для генерации Select From части запроса
 * 
 * @author hindu
 */

public class QueryData {
    private IdTable table;
    private List<SelectFrom> data;
    private List<Set> set;

    {
	data = new LinkedList<SelectFrom>();
	set = new LinkedList<Set>();
    }

    public QueryData(IdTable table) {
	this.table = table;
	prepare();
    }

    public List<SelectFrom> getData() {
	return data;
    }

    private void prepare() {
	List<Identifier> ids = table.getIds();

	for (Identifier id : ids) {
	    if (id.getSrcType() == Type.db) {
		addSelFrom(id);
	    }
	    else if (id.getSrcType() == Type.set) {
		addSet(id);
	    }
	}
    }

    private void addSet(Identifier id) {
	set.add((Set) id.getSrcData());
    }

    private void addSelFrom(Identifier id) {
	boolean added = false;
	Database db = (Database) id.getSrcData();

	for (SelectFrom selFrom : data) {
	    if (selFrom.getTable().contentEquals(db.getTable())) {
		selFrom.addColumn(db.getColumn(), id.getAlias());
		added = true;
	    }
	}

	if (!added) {
	    SelectFrom selFrom = new SelectFrom();

	    selFrom.setTable(db.getTable());
	    selFrom.addColumn(db.getColumn(), id.getAlias());

	    data.add(selFrom);
	}
    }

    public List<Set> getSet() {
        return set;
    }

    public void setSet(List<Set> set) {
        this.set = set;
    }
}
