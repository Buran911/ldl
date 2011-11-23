package parse.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import application.util.StackTrace;

/**
 * Класс содержащий информацию об исходных файлах и предоставляющий методы
 * навигации
 * 
 * @author exellent
 * @author Hindu
 * */

public class LDLfile {
    private String path;
    private String[] programLines; // программа построчно
    private int lineCount;
    private int beginIndex; // Начало файла в глобальной нумерации строк
    private int endIndex; // Конец файла в глобальной нумерации строк
			  // включительно
    private Logger logger = Logger.getLogger(LDLfile.class);

    public LDLfile(String path) {
	this.path = path;
	try {
	    readContent();
	} catch (FileNotFoundException e) {
	    logger.error("Исходный файл:" + path + " не найден.");
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

    /** начиная с globalLineNo получить непустую строку */
    public String getLine(Integer globalLineNo) {
	if (getLineNo(globalLineNo) == null)
	    return "";

	return programLines[getLineNo(globalLineNo)];
    }

    /** начиная с globalLineNo получить номер непустой строки */
    public Integer getLineNo(Integer globalLineNo) {
	return getUnemptyLinePos(getLocalLineNo(globalLineNo));
    }

    /** начиная с globalLineNo получить контекст непустой строки */
    public String getLineContext(Integer globalLineNo) {
	if ((getLineContextNo(globalLineNo) == null) || (getLineContextNo(globalLineNo) == -1)) {
	    return "";
	}

	return programLines[getLineContextNo(globalLineNo)];
    }

    /** начиная с globalLineNo получить номер контекста непустой строки */
    public Integer getLineContextNo(Integer globalLineNo) {
	return getUnemptyLinePos(getLineNo(globalLineNo) - 1);
    }

    public String getFileName() {
	return path;
    }

    private void readContent() throws FileNotFoundException {
	BufferedReader in = new BufferedReader(new FileReader(new File(path)));
	String line;
	String content = "";
	try {
	    while ((line = in.readLine()) != null) {
		if (line.matches("[ \\t]+")) {
		    line = "";
		}
		content += line + "\n";
	    }
	} catch (IOException e) {
	    logger.error("Невозможно считать файл:" + path);
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