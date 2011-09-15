package testcases.functional;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import parse.util.Source;


public class SourceFiles {
	private static Source src;
	
	@BeforeClass
	public static void setUp(){
		src = new Source("testdata/sourcetest/1.ldl",
							"testdata/sourcetest/2.ldl",
							"testdata/sourcetest/3.ldl");
	}
	
	@Test
	public void getLine(){
		String line31 = "context Participant{";
		String line56 = "context BIC{";
		
		Assert.assertEquals(line31, src.getLine(31));
		Assert.assertEquals(line56, src.getLine(56));
	}
	
	@Test
	public void getUnemptyLine(){
		String line17 = "	}";
		String line33 = "	account instanceof Account;";
		String line55 = null;
		
		Assert.assertEquals(line17, src.getUnemptyLine(18));
		Assert.assertEquals(line33, src.getUnemptyLine(34));
		Assert.assertEquals(line55, src.getUnemptyLine(55));
	}
	
	@Test
	public void getUnemptyContext(){
		String line3 = "	payee instanceof Participant;";
		String line16 = "		Participiants_in_diff_regions();";
		
		Assert.assertEquals(line3, src.getUnemptyContext(5));
		Assert.assertEquals(line16, src.getUnemptyContext(18));
	}
	
	@Test
	public void getFileName(){
		String file1 = "testdata/sourcetest/1.ldl";
		String file2 = "testdata/sourcetest/2.ldl";
		
		Assert.assertEquals(file1, src.getFileName(28));
		Assert.assertEquals(file2, src.getFileName(31));
	}
	

}
