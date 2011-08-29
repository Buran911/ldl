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

public class Source {
	private String program = ""; // ��������� ����� �������
	private String[] programLines; // ��������� ���������
	private ArrayList<LDLfile> files = new ArrayList<LDLfile>();
	private ArrayList<Integer> filePos = new ArrayList<Integer>(); // XXX �� ����� ������ ��������� ������ ������� � ������ ������
	
	public Source(String ... arg){
		for(String path : arg){
			files.add(new LDLfile(path));
		}
		// ������ ���� � ������� ���� ���������
		for(LDLfile file : files){
			program += file.getContent();
		}
		

		programLines = program.split("\\n");
		
		//  "����������" ������ �������� � �����
		for(int i = 0; i < programLines.length; i++){
			if(programLines[i].matches("[ \\t]+")){
				programLines[i] = "";
			}
		}
		
		// ������������� �����
		filePos.add(-1); // ������������
		for(LDLfile file : files){
			filePos.add(file.getLineCount() + filePos.get( filePos.size() -1 ));
		}
		filePos.remove(0); // ���������, ������� ������������
	}
	
	public String getProgram() {
		return program;
	}

	public String getLine(int lineNo){
		return programLines[lineNo];
	}
	/**
	 * ������ ����� ������ �� ������ ������. ��������� ���� ��������� �������� ������
	 * ���� �������� ������ ������� ���� ��� - ���������� null.
	 * */
	public String getUnemptyLine(int lineNo){
		Integer pos = getUnemptyLinePos(lineNo);
		
		// ���� �������� ������ �� �������
		if (pos == null){
			return null;
		}
		
		return programLines[pos];
	}
	
	/**
	 * ����� ������ ��� ������� ����� ���� ��������� ������ �����.
	 * ��� ����� ������������ ������ ����� ������� ������ ��� �������.
	 * �� ��������� ����� ������ ��������� ������� ��������� �������� ������.
	 * ���� �������� ������ ������� ���� ��� - ���������� null.
	 * */
	public String getUnemptyContext(int lineNo){
		Integer targetPos = getUnemptyLinePos(lineNo);
		if (targetPos == null){
			return null;
		}
		
		Integer targetContextPos = getUnemptyLinePos(targetPos - 1);
		if (targetContextPos == null){
			return null;
		}
		
		return programLines[targetContextPos];
	}
	
	public String getFileName(Integer lineNo){
		int pos = 0;
		
		while( lineNo >= filePos.get(pos)){
			pos++;
		}
		
		return files.get(pos).getPath();
	}
	

	private Integer getUnemptyLinePos( Integer lineNo){
		Integer pos = lineNo;
		while(programLines[pos].length() == 0){
			if(filePos.contains(pos)){
				return null;
			}
			
			pos--;
			
			if(pos == -1){
				return null;
			}
		}
		
		return pos;
	}

	class LDLfile{
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

		private void readContent() throws FileNotFoundException{
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


