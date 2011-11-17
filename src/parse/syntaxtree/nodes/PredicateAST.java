package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;
import parse.util.Positionable;

public class PredicateAST extends BinaryExpAST implements Positionable {
    private AttributeCallAST attrCall;
    private VariableAST variable;
    private OperationCallAST oprCall;
    private PredicateImplAST impl;
    private Integer lineNo;
    private Integer columnNo;

    public void setAttrCall(AttributeCallAST attrCall) {
	this.attrCall = attrCall;
	addSuccessor(attrCall);
    }

    public void setVariable(VariableAST variable) {
	this.variable = variable;
	addSuccessor(variable);
    }

    public void setOprCall(OperationCallAST oprCall) {
	this.oprCall = oprCall;
	addSuccessor(oprCall);
    }

    public AttributeCallAST getAttrCall() {
	return attrCall;
    }

    public VariableAST getVariable() {
	return variable;
    }

    public OperationCallAST getOprCall() {
	return oprCall;
    }

    public PredicateImplAST getImpl() {
	return impl;
    }

    public void setImpl(PredicateImplAST impl) {
	this.impl = impl;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    @Override
    public Integer getLineNo() {
	return lineNo;
    }

    public void setLineNo(Integer lineNo) {
	this.lineNo = lineNo;
    }

    @Override
    public Integer getColumnNo() {
	return columnNo;
    }

    public void setColumnNo(Integer columnNo) {
	this.columnNo = columnNo;
    }

    @Override
    public PredicateAST clone() {
	PredicateAST copy = new PredicateAST();
	if (attrCall != null) {
	    copy.attrCall = (AttributeCallAST) attrCall.clone();
	}
	if (variable != null) {
	    copy.variable = variable.clone();
	}
	copy.oprCall = (OperationCallAST) oprCall.clone();
	if (impl != null) {
	    // FIXME impl должен ссылаться на объект своего дерева
	    copy.impl = (PredicateImplAST) impl.clone();
	}
	copy.lineNo = (lineNo != null) ? lineNo : null;
	copy.columnNo = (columnNo != null) ? columnNo : null;

	return copy;
    }
}
