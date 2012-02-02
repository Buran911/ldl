package parse.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import application.util.Halt;

public class FileReader {
    private BufferedReader in;

    public FileReader(String pathToFile) throws FileNotFoundException {
	in = new BufferedReader(new java.io.FileReader(pathToFile));
    }

    public static String readFile(String pathToFile) throws FileNotFoundException {
	FileReader fileReader = new FileReader(pathToFile);
	String content = "";
	String line;

	while ((line = fileReader.nextLine()) != null) {
	    content += line + "\n";
	}
	
	fileReader.close();
	return content;
    }

    public void close() {
	try {
	    in.close();
	} catch (IOException e) {
	    throw new Halt();
	}
    }

    public String nextLine() {
	try {
	    return in.readLine();
	} catch (IOException e) {
	    throw new Halt();
	}
    }
}
