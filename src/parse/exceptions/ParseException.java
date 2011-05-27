package parse.exceptions;

/**
 * ����� ����� ��� ���� ������ ��������,
 * �������� ������� ������.
 * 
 * @author hindu
 * */
public abstract class ParseException extends Exception{
	private String sourceFileName; // ����, � ������� �������� ������
	private int lineNo; // �������������� ������� ������ � ���������
	
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
