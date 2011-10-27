package parse.parsetree;


public abstract class PositionableNode extends Node {
	private Integer lineNo;
	private Integer columnNo;
	
	public PositionableNode(Integer lineNo, Integer columnNo) {
		super();
		this.lineNo = lineNo;
		this.columnNo = columnNo;
	}
	public Integer getLineNo() {
		return lineNo;
	}
	public Integer getColumnNo() {
		return columnNo;
	}
}
