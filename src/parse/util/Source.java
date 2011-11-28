/**
 * Класс содержит в себе исходники ldl, 
 * объединяет их в 1 файл и предоставляет возможности навигации по нему.
 * */

package parse.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс инкапсулирует исходные файлы и обеспечивает навигацию по ним.
 * 
 * @author hindu
 * */
public class Source {
    private ArrayList<LDLfile> files = new ArrayList<LDLfile>();

    public Source(String... arg) {
	for (String path : arg) {
	    files.add(new LDLfile(path));
	}

	preprocess();
    }

    public Source(List<String> paths) {
	for (String path : paths) {
	    files.add(new LDLfile(path));
	}

	preprocess();
    }

    public String getProgram() {
	String program = "";
	for(LDLfile file : files ){
	    program += file.getFileData();
	}
	return program;
    }

    public String getLine(Integer globalLineNo) {
	LDLfile file = getFile(globalLineNo);

	return file.getLine(globalLineNo);
    }

    public Integer getLineNo(Integer globalLineNo) {
	LDLfile file = getFile(globalLineNo);
	
	return file.getLineNo(globalLineNo);
    }

    public String getLineContext(Integer globalLineNo) {
	LDLfile file = getFile(globalLineNo);
	
	return file.getLineContext(globalLineNo);
    }

    public Integer getLineContextNo(Integer globalLineNo) {
	LDLfile file = getFile(globalLineNo);
	
	return file.getLineContextNo(globalLineNo);
    }

    public String getFileName(Integer globalLineNo) {
	LDLfile file = getFile(globalLineNo);
	
	return file.getFileName();
    }

    private LDLfile getFile(Integer globalLineNo) {
	for (LDLfile file : files) {
	    if ((file.getBeginIndex() <= globalLineNo) && (globalLineNo <= file.getEndIndex())) {
		return file;
	    }
	}
	return null;
    }

    private void preprocess() {
	// позиционируем файлы
	int beginIndex = 0;
	int endIndex = -1;// Выравнивание для первой итерации
	for (LDLfile file : files) {
	    beginIndex = endIndex + 1;
	    endIndex = beginIndex + file.getLineCount() - 1;

	    file.setBeginIndex(beginIndex);
	    file.setEndIndex(endIndex);
	}
    }
}
