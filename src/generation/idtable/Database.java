package generation.idtable;

public class Database extends SourceData {
    private String table;
    private String column;

    public String getTable() {
	return table;
    }

    public void setTable(String table) {
	this.table = table;
    }

    public String getColumn() {
	return column;
    }

    public void setColumn(String column) {
	this.column = column;
    }
    
    @Override
    public Object clone() {
	Database copy = new Database();

	copy.table = new String(table);
	copy.column = new String(column);

	return copy;
    }
}
