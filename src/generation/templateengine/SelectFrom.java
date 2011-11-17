package generation.templateengine;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс содержит в себе информацию для генерирования cross join запроса.
 * @author hindu
 * */
public class SelectFrom {
    private List<Column> columns;
    private String table;

    {
	columns = new LinkedList<SelectFrom.Column>();
    }

    public String getTable() {
	return table;
    }

    public List<Column> getColumns() {
	return columns;
    }

    public void addColumn(String name, String alias) {
	columns.add(new Column(name, alias));
    }

    public void setTable(String table) {
	this.table = table;
    }

    class Column {
	private String name;
	private String alias;

	public Column(String name, String alias) {
	    this.name = name;
	    this.alias = alias;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getAlias() {
	    return alias;
	}
    }
}
