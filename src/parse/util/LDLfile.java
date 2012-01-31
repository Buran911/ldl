package parse.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.stringtemplate.v4.compiler.CodeGenerator.includeExpr_return;

import application.util.StackTrace;

/**
 * ����� ���������� ���������� �� �������� ������ � ��������������� ������
 * ���������
 * 
 * @author exellent
 * @author Hindu
 * */

public class LDLfile {
    private String path;
    private String[] programLines; // ��������� ���������
    private int lineCount;
    private int beginIndex; // ������ ����� � ���������� ��������� �����
    private int endIndex; // ����� ����� � ���������� ��������� �����
			  // ������������
    private Logger logger = Logger.getLogger(LDLfile.class);

    public LDLfile(String path) {
	this.path = path;
	try {
	    readContent();
	} catch (FileNotFoundException e) {
	    logger.error("�������� ����:" + path + " �� ������.");
	    logger.trace(StackTrace.getStackTrace(e));
	}
    }

    public String getPath() {
	return path;
    }

    public String getFileData() {
	String data = "";
	for (String line : programLines) {
	    data += line + "\n";
	}

	return data;
    }

    public int getLineCount() {
	return lineCount;
    }

    public int getBeginIndex() {
	return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
	this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
	return endIndex;
    }

    public void setEndIndex(int endIndex) {
	this.endIndex = endIndex;
    }

    /** ������� � globalLineNo �������� �������� ������ */
    public String getLine(Integer globalLineNo) {
	if (getLineNo(globalLineNo) == null)
	    return "";

	return programLines[getLineNo(globalLineNo)];
    }

    /** ������� � globalLineNo �������� ����� �������� ������ */
    public Integer getLineNo(Integer globalLineNo) {
	return getUnemptyLinePos(getLocalLineNo(globalLineNo));
    }

    /** ������� � globalLineNo �������� �������� �������� ������ */
    public String getLineContext(Integer globalLineNo) {
	if ((getLineContextNo(globalLineNo) == null) || (getLineContextNo(globalLineNo) == -1)) {
	    return "";
	}

	return programLines[getLineContextNo(globalLineNo)];
    }

    /** ������� � globalLineNo �������� ����� ��������� �������� ������ */
    public Integer getLineContextNo(Integer globalLineNo) {
	return getUnemptyLinePos(getLineNo(globalLineNo) - 1);
    }

    public String getFileName() {
	return path;
    }

    private void readContent() throws FileNotFoundException {
	String content = "";
	BufferedReader in = null;

	try {
	    try {
		in = new BufferedReader(new FileReader(new File(path)));
		String line;
		while ((line = in.readLine()) != null) {
		    if (line.matches("[ \\t]+")) {
			line = "";
		    }
		    content += line + "\n";
		}
	    } finally {
		in.close();
	    }

	} catch (IOException e) {
	    // FIXME ��� ������ ������ �������.
	    logger.error("���������� ������� ����:" + path);
	    logger.trace(StackTrace.getStackTrace(e));
	}

	programLines = content.split("\n");
	lineCount = programLines.length;
    }

    private int getLocalLineNo(Integer globalLineNo) {
	return globalLineNo - beginIndex;
    }

    private Integer getUnemptyLinePos(Integer localLineNo) {
	if (localLineNo < 0) {
	    return null;
	}
	Integer pos = localLineNo;
	while (programLines[pos].length() == 0) {
	    pos--;
	    if (pos == -1) {
		return null;
	    }
	}

	return pos;
    }
}