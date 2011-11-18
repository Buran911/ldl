/**
 * ����� �������� � ���� ��������� ldl, 
 * ���������� �� � 1 ���� � ������������� ����������� ��������� �� ����.
 * */

package parse.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ����� ������������� �������� ����� � ���������������� �� ���.
 * 
 * @author hindu
 * */
public class Source {
    private String program = ""; // ��������� ����� �������
    private String[] programLines; // ��������� ���������
    private ArrayList<LDLfile> files = new ArrayList<LDLfile>();
    private ArrayList<Integer> filePos = new ArrayList<Integer>(); // XXX �� ����� ������ ��������� ������ ������� � ������ ������
    
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
	return program;
    }

    public String getLine(int lineNo) {
	return programLines[lineNo];
    }

    public String getContext(int lineNo){
	
	return getUnemptyLine(lineNo-1);
    }
    /**
     * ������ ����� ������ �� ������ ������. ��������� ���� ��������� ��������
     * ������ ���� �������� ������ ������� ���� ��� - ���������� null.
     * */
    public String getUnemptyLine(int lineNo) {
	Integer pos = getUnemptyLinePos(lineNo);

	// ���� �������� ������ �� �������
	if (pos == null) {
	    return null;
	}

	return programLines[pos];
    }

    /**
     * ����� ������ ��� ������� ����� ���� ��������� ������ �����. ��� �����
     * ������������ ������ ����� ������� ������ ��� �������. �� ��������� �����
     * ������ ��������� ������� ��������� �������� ������. ���� �������� ������
     * ������� ���� ��� - ���������� null.
     * */
    public String getUnemptyContext(int lineNo) {
	Integer targetPos = getUnemptyLinePos(lineNo);
	if (targetPos == null) {
	    return null;
	}

	Integer targetContextPos = getUnemptyLinePos(targetPos - 1);
	if (targetContextPos == null) {
	    return null;
	}

	return programLines[targetContextPos];
    }

    public Integer getContextPos(int lineNo) {
	Integer targetPos = getUnemptyLinePos(lineNo);
	if (targetPos == null) {
	    return null;
	}

	Integer targetContextPos = getUnemptyLinePos(targetPos - 1);
	if (targetContextPos == null) {
	    return null;
	}
	
	return targetContextPos - getFilePos(lineNo);
    }
    
    private Integer getFilePos(int lineNo){
	int pos = 0;
	while (lineNo >= filePos.get(pos)) {
	    pos++;
	}
	
	if(pos == 0){
	    return 0;
	}
	
	return filePos.get(pos-1);
    }
     
    public String getFileName(Integer lineNo) {
	int pos = 0;
	while (lineNo > filePos.get(pos)) {
	    pos++;
	}

	return files.get(pos).getPath();
    }

    public Integer getLinePos(Integer lineNo){
	int pos = 0;
	
	while (lineNo >= filePos.get(pos)) {
	    pos++;
	}
	
	int lineCount = (pos != 0) ? filePos.get(pos - 1) : 0; 
	return (lineNo - lineCount);
    }
    
    private Integer getUnemptyLinePos(Integer lineNo) {
	Integer pos = lineNo;
	while (programLines[pos].length() == 0) {
	    if (filePos.contains(pos)) {
		return null;
	    }

	    pos--;

	    if (pos == -1) {
		return null;
	    }
	}

	return pos;
    }

    private void preprocess() {
	// ������ ���� � ������� ���� ���������
	for (LDLfile file : files) {
	    program += file.getContent();
	}

	programLines = program.split("\\n");

	// "����������" ������ �������� � �����
	for (int i = 0; i < programLines.length; i++) {
	    if (programLines[i].matches("[ \\t]+")) {
		programLines[i] = "";
	    }
	}

	// ������������� �����
	filePos.add(0); // ������������
	for (LDLfile file : files) {
	    filePos.add(file.getLineCount() + filePos.get(filePos.size() - 1));
	}
	filePos.remove(0); // ���������, ������� ������������
    }

    class LDLfile {
	private String path;
	private String content;
	private int lineCount;

	public LDLfile(String path) {
	    this.path = path;
	    try {
		readContent();
	    } catch (FileNotFoundException e) {
		System.err.println("�������� ����:" + path + " �� ������.");
		e.printStackTrace();
	    }
	}

	public String getPath() {
	    return path;
	}

	public String getContent() {
	    return content;
	}

	public int getLineCount() {
	    return lineCount;
	}

	private void readContent() throws FileNotFoundException {
	    BufferedReader in = new BufferedReader(new FileReader(new File(path)));
	    String line;
	    content = "";
	    lineCount = 0;

	    // FIXME ��������� \n �� �����������, �� ����� �� �� ��� �� ������
	    try {
		while ((line = in.readLine()) != null) {
		    content += line + "\n";
		    lineCount++;
		}
	    } catch (IOException e) {
		System.err.println("���������� ������� ����:" + path);
		e.printStackTrace();
	    }

	}
    }

}
