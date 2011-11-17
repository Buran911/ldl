/**
 * Класс содержит в себе исходники ldl, 
 * объединяет их в 1 файл и предоставляет возможности навигации по нему.
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
 * Класс инкапсулирует исходные файлы и позиционирование по ним.
 * 
 * @author hindu
 * */
public class Source {
    private String program = ""; // программа одной строкой
    private String[] programLines; // программа построчно
    private ArrayList<LDLfile> files = new ArrayList<LDLfile>();
    private ArrayList<Integer> filePos = new ArrayList<Integer>(); // XXX не очень хорошо связывать индесы массива и номера файлов
    
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

    /**
     * Парсер может упасть на пустой строке. Программа ищет последнюю непустую
     * строку Если непустой строки спереди таки нет - возвращаем null.
     * */
    public String getUnemptyLine(int lineNo) {
	Integer pos = getUnemptyLinePos(lineNo);

	// Если непустая строка не найдена
	if (pos == null) {
	    return null;
	}

	return programLines[pos];
    }

    /**
     * Перед нужной нам строкой может быть несколько пустых строк. Для более
     * осмысленного вывода стоит вывести строки над пустыми. За отправную точку
     * поиска контекста берется последняя непустая строка. Если непустой строки
     * спереди таки нет - возвращаем null.
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

    public String getFileName(Integer lineNo) {
	int pos = 0;

	while (lineNo >= filePos.get(pos)) {
	    pos++;
	}

	return files.get(pos).getPath();
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
	// делаем файл с текстом всей программы
	for (LDLfile file : files) {
	    program += file.getContent();
	}

	programLines = program.split("\\n");

	// "опустошаем" строки пробелов и табов
	for (int i = 0; i < programLines.length; i++) {
	    if (programLines[i].matches("[ \\t]+")) {
		programLines[i] = "";
	    }
	}

	// позиционируем файлы
	filePos.add(-1); // выравнивание
	for (LDLfile file : files) {
	    filePos.add(file.getLineCount() + filePos.get(filePos.size() - 1));
	}
	filePos.remove(0); // выровняли, убираем выравнивание
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
		System.err.println("Исходный файл:" + path + " не найден.");
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

	    // FIXME Последний \n не считывается, де факто ни на что не влияет
	    try {
		while ((line = in.readLine()) != null) {
		    content += line + "\n";
		    lineCount++;
		}
	    } catch (IOException e) {
		System.err.println("Невозможно считать файл:" + path);
		e.printStackTrace();
	    }

	}
    }

}
