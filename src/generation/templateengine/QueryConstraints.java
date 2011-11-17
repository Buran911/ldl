package generation.templateengine;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.nodes.ConstraintAST;

/**
 * Класс работающий как генератор, выдает шаблонизатору набор ограничений для
 * построения where части запроса. 
 * @author hindu
 * */
public class QueryConstraints {
    private EqualityClass constPart;
    private List<EqualityClass> eqClasses;
    private Iterator<EqualityClass> itEqClass;
    private boolean fistRun;

    {
	eqClasses = new LinkedList<EqualityClass>();
	itEqClass = eqClasses.iterator();
	fistRun = true;
    }

    public EqualityClass getConstPart() {
	return constPart;
    }

    public void setConstPart(EqualityClass constPart) {
	this.constPart = constPart;
    }

    public void addEqualityClass(EqualityClass eqClass) {
	eqClasses.add(eqClass);
    }

    public EqualityClass getEqClass(int index) {
	return eqClasses.get(index);
    }

    public void duplicate(int cloneCount) {
	List<EqualityClass> newEqClasses = eqClasses;
	int eqClassesCount = eqClasses.size();

	for (int i = 0; i < cloneCount - 1; i++) {
	    newEqClasses.addAll(getEqClassesClone(eqClassesCount));
	}

	eqClasses = newEqClasses;
    }

    public int getEqClassesCount() {
	return eqClasses.size();
    }

    public void makeUnmodifiable() {
	eqClasses = Collections.unmodifiableList(eqClasses);
	itEqClass = eqClasses.iterator();
    }

    public List<ConstraintAST> next() {
	List<ConstraintAST> constraints = new LinkedList<ConstraintAST>();

	constraints.addAll(constPart.getConstraints());
	if (itEqClass.hasNext()) {
	    EqualityClass varPart = itEqClass.next();
	    constraints.addAll(varPart.getConstraints());
	}

	return constraints;
    }

    private List<EqualityClass> getEqClassesClone(int elementCount) {
	List<EqualityClass> ec = new LinkedList<EqualityClass>();

	for (int i = 0; i < elementCount; i++) {
	    ec.add((EqualityClass) eqClasses.get(i).clone());
	}

	return ec;
    }

    public boolean hasNext() {
	if (fistRun) {
	    fistRun = false;

	    return true;
	}

	return itEqClass.hasNext();
    }

}
