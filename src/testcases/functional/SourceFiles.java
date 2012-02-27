package testcases.functional;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import parse.util.Source;

public class SourceFiles {
    private static Source src;

    @BeforeClass
    public static void setUp() {
	src = new Source("testdata/sourcetest/1.ldl", "testdata/sourcetest/2.ldl", "testdata/sourcetest/3.ldl");
    }

    @Test
    public void getLine() {
	String line17 = "	}";
	String line31 = "context Participant{";
	String line33 = "	account instanceof Account;";
//	String line54 = "";
	String line54 = "context BIC{";
	Assert.assertEquals(line17, src.getLine(18));
	Assert.assertEquals(line31, src.getLine(31));
	Assert.assertEquals(line33, src.getLine(34));
//	Assert.assertEquals(line54, src.getLine(54));
	Assert.assertEquals(line54, src.getLine(54));
    }

    @Test
    public void getLineContext() {
	String line3 = "	payee instanceof Participant;";
	String line16 = "		Participiants_in_diff_regions();";

	Assert.assertEquals(line3, src.getLineContext(5));
	Assert.assertEquals(line16, src.getLineContext(18));
    }

    @Test
    public void getFileName() {
	String file1 = "testdata/sourcetest/1.ldl";
	String file2 = "testdata/sourcetest/2.ldl";

	Assert.assertEquals(file1, src.getFileName(28));
	Assert.assertEquals(file2, src.getFileName(31));
    }
}