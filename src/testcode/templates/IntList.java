package testcode.templates;

import java.util.LinkedList;
import java.util.List;

public class IntList {
	private List<Integer> data;
	
	{
		data = new LinkedList<Integer>();
		
		data.add(new Integer(1));
		data.add(new Integer(2));
		data.add(new Integer(3));
	}

	public List<Integer> getData() {
		return data;
	}
}
