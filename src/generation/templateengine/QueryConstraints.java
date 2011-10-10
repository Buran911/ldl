package generation.templateengine;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.nodes.ConstraintAST;


/** 
 * Класс работающий как генератор, выдает шаблонизатору набор
 * ограничений для построения where части запроса.
 * */
public class QueryConstraints {
	private EqualityClass constPart;
	private boolean hasNnext;
	private List<EqualityClass> eqClasses;
	private Iterator<EqualityClass> itEqClass;

	{
		eqClasses = new LinkedList<EqualityClass>();
		hasNnext = false;
		itEqClass = eqClasses.iterator();
	}
	
	public EqualityClass getConstPart() {
		return constPart;
	}


	public void setConstPart(EqualityClass constPart) {
		this.constPart = constPart;
		
		if(hasNnext == false){
			hasNnext = true;
		}
	}


	public void addEqualityClass(EqualityClass eqClass){
		eqClasses.add(eqClass);
		
		if(hasNnext == false){
			hasNnext = true;
		}
	}
	
	
	public List<EqualityClass> getEqClasses() {
		return eqClasses;
	}
	
	public boolean hasNnext(){
		return hasNnext;
	}
	
	public List<ConstraintAST> getGnext(){
		List<ConstraintAST> constraints = new LinkedList<ConstraintAST>();
		
		constraints.addAll(constPart.getConstraints());
		if(itEqClass.hasNext()){
			constraints.addAll( itEqClass.next().getConstraints() );
		}
		hasNnext = itEqClass.hasNext();
		
		return constraints;
	}
}
