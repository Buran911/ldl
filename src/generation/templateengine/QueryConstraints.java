package generation.templateengine;

import java.util.LinkedList;
import java.util.List;

public class QueryConstraints {
	public List<EqualityClass> eqClasses;

	{
		eqClasses = new LinkedList<EqualityClass>();
	}
	
	public void addEqualityClass(EqualityClass eqClass){
		eqClasses.add(eqClass);
	}
	
	
	public List<EqualityClass> getEqClasses() {
		return eqClasses;
	}
}
