package application.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;

/**
 * ����� ��� �������� ���������� ��������� ������ ����
 * "<program_name> -p <property_file_name.xml> -s <file1.ldl ... fileN.ldl>"
 * 
 * @author exellent
 */
public class CmdLineParser {
    private Logger logger = Logger.getLogger(CmdLineParser.class);
    private CommandLineParser parser = new PosixParser();
    private Options options = new Options();
    private HelpFormatter formatter = new HelpFormatter();
    private CommandLine cmd;
    private String args[];

    @SuppressWarnings("static-access")
    private Option property = OptionBuilder.withArgName("propertyFileName.xml").hasArgs(1).isRequired().withDescription("����, ���������� ��������� ������ ��� ���").create("p");

    @SuppressWarnings("static-access")
    private Option files = OptionBuilder.hasArgs().isRequired().withArgName("file1.ldl file2.ldl ... fileN.ldl")
	    .withDescription("�����, ���������� ���������� �������� �������� �������").create("s");

    @SuppressWarnings("static-access")
    private Option pyDir = OptionBuilder.hasArgs(1).withDescription("���� � ���������� � ������� python").create("py");

    private String propertyFile;
    private List<String> ldlFiles = new ArrayList<String>();
    private String pythonDir;

    public CmdLineParser(String args[]) {
	this.args = args;

	options.addOption(files);
	options.addOption(property);
	options.addOption(pyDir);
    }

    /** ������� ������� ������ */
    public boolean parse() {
	try {
	    cmd = parser.parse(options, args);

	    if (cmd.getOptionValue("p").endsWith(".xml")) {
		propertyFile = cmd.getOptionValue("p");
	    }
	    else {
		throw new ParseException("�������� ���������� ����� xml");
	    }

	    String[] fileList = cmd.getOptionValues("s");

	    for (String file : fileList) {
		if (file.endsWith(".ldl")) {
		    ldlFiles.add(file);
		}
		else {
		    throw new ParseException("�������� ���������� �����(��) ldl");
		}
	    }

	    pythonDir = cmd.getOptionValue("py");

	} catch (ParseException exp) {
	    logger.error(exp.getMessage());
	    this.help();

	    return false;
	}

	return true;
    }

    // ������� iterator'� �� ������ ������ ldl
    public Iterator<String> getFileListIterator() {
	return ldlFiles.iterator();
    }

    public List<String> getLdlFiles() {
	return ldlFiles;
    }

    // ������� ����� xml
    public String getPropertyFile() {
	return propertyFile;
    }

    public String getPythonDir() {
	return pythonDir;
    }

    private void help() {
	formatter.printHelp("<program_name> -p <property_file_name.xml> -s <file1.ldl ... fileN.ldl>", "������ �������� ������� ��������������� ���������� ��������� ����������",
		options, null);
    }
}