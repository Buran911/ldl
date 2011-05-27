package parse.exceptions;

/**
 * Общий класс для всех ошибок парсинга,
 * содержит позицию ошибки.
 * 
 * @author hindu
 * */
public abstract class ParseException extends Exception{
	private String sourceFileName; // файл, в котором возникла ошибка
	private int lineNo; // предпологаемая позиция ошибки в исходнике
	
	public ParseException(String sourceFileName, int lineNo){
		this.sourceFileName = sourceFileName;
		this.lineNo = lineNo;
	}
	
	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public int getLineNo() {
		return lineNo;
	}

	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}
	
	
}
