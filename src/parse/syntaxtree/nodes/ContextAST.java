package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;

public class ContextAST extends NodeAST {
    private SimpleNameAST contextName;
    private List<DescriptionAST> descriptions;
    private List<SourceAST> sources;
    private List<ConstraintAST> constraints;
    private List<EqClassAST> eqClasses;

    {
	descriptions = new LinkedList<DescriptionAST>();
	sources = new LinkedList<SourceAST>();
	constraints = new LinkedList<ConstraintAST>();
	eqClasses = new LinkedList<EqClassAST>();
    }

    public void setContextName(SimpleNameAST contextName) {
	this.contextName = contextName;
	addSuccessor(contextName);
    }

    public void addDescription(DescriptionAST description) {
	descriptions.add(description);
	addSuccessor(description);
    }

    public void addSource(SourceAST source) {
	sources.add(source);
	addSuccessor(source);
    }

    public void addConstraint(ConstraintAST constraint) {
	constraints.add(constraint);
	addSuccessor(constraint);
    }

    public void addEqClass(EqClassAST eqClass) {
	eqClasses.add(eqClass);
	addSuccessor(eqClass);
    }

    public SimpleNameAST getContextName() {
	return contextName;
    }

    public List<DescriptionAST> getDescriptions() {
	return descriptions;
    }

    public List<SourceAST> getSources() {
	return sources;
    }

    public List<ConstraintAST> getConstraints() {
	return constraints;
    }

    public List<EqClassAST> getEqClasseses() {
	return eqClasses;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }
    
    @Override
    public Object clone() {

	ContextAST copy = new ContextAST();
	copy.setContextName((SimpleNameAST) contextName.clone());
	for (DescriptionAST description : descriptions) {
	    copy.addDescription((DescriptionAST) description.clone());
	}
	for (SourceAST source : sources) {
	    copy.addSource((SourceAST) source.clone());
	}
	for (ConstraintAST constraint : constraints) {
	    copy.addConstraint((ConstraintAST)constraint.clone());
	}
	for (EqClassAST eqClass : eqClasses) {
	    copy.addEqClass((EqClassAST) eqClass.clone());
	}
	return copy;
    }
}
