package parse.syntaxtree.nodes;

import parse.syntaxtree.NodeAST;
import parse.util.Positionable;
import generation.walkers.TreeWalker;

public class PredicateAST extends BinaryExpAST implements Positionable, Cloneable {
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

    public PredicateAST clone() {
	PredicateAST copy = new PredicateAST();
	copy.attrCall = this.attrCall;
	copy.variable = this.variable;
	copy.oprCall = this.oprCall;
	copy.impl = this.impl;
	copy.lineNo = this.lineNo;
	copy.columnNo = this.columnNo;

	return copy;
    }
}
