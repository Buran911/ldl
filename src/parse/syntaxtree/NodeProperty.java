package parse.syntaxtree;

import java.io.Serializable;

/**
 * ����� �������� � ���� ���� ����� ���. ���������� �������������.
 * @author hindu
 * */
public class NodeProperty implements Serializable {
    /**
     * @see NodeTypeVisitor ������ �������� isNode, isLeaf, etc ��������������� �
     *      ���� ������.
     */
    private boolean isNode; // true, ���� � ������� ���� "����" � �����
			    // ���������� �� ���������
    private boolean isLeaf; // true, ���� ��� ����
    private boolean isResultDefenition; // true, ���� ������ ����
					// AbstractResultDefenitionAST
    private boolean isAttributeCallExp; // true, ���� ������ ����
					// AttributeCallExprssionAST
    private boolean isVariableExp; // true, ���� ������ ���� VariableExpAST
    private boolean isBoolExp; // true, ���� ��� ����� ������� includes
    private boolean isDummy; // true, ���� ��� "��������" � ������ ���� �� ����
    private boolean isFunction; // true, ���� ��� ����� �������
    private boolean isPredicate; // true, ���� ��� ����� ���������
    private boolean isString; // true, ���� ��� ��������� ��� ������

    private boolean used; // true, ��������� ��� �������������, ���� �������
			  // ������ ��� ���������

    {
	this.isNode = false;
	this.isLeaf = false;
	this.isResultDefenition = false;
	this.isAttributeCallExp = false;
	this.isVariableExp = false;
	this.isBoolExp = false;
	this.used = false;
    }

    public NodeProperty() {
	this.isNode = false;
	this.isLeaf = false;
	this.isAttributeCallExp = false;
    }

    public boolean isResultDefenition() {
	return isResultDefenition;
    }

    public void setResultDefenition() {
	this.isResultDefenition = true;
    }

    public boolean isBoolExp() {
	return isBoolExp;
    }

    public void setBoolExp() {
	this.isBoolExp = true;
    }

    public boolean used() {
	return used;
    }

    public void setUsed() {
	this.used = true;
    }

    public void setUnused() {
	this.used = false;
    }

    public boolean isVariableExp() {
	return isVariableExp;
    }

    public void setVariableExp() {
	this.isVariableExp = true;
    }

    public boolean isNode() {
	return isNode;
    }

    public void setNode() {
	this.isNode = true;
    }

    public void setNodeFalse() {
	this.isNode = false;
    }

    public boolean isLeaf() {
	return isLeaf;
    }

    public void setLeaf() {
	this.isLeaf = true;
    }

    public boolean isAttributeCallExp() {
	return isAttributeCallExp;
    }

    public void setAttributeCallExp() {
	this.isAttributeCallExp = true;
    }

    public boolean isDummy() {
	return isDummy;
    }

    public void setDummy() {
	this.isDummy = true;
    }

    public boolean isFunction() {
	return isFunction;
    }

    public void setFunction() {
	this.isFunction = true;
    }

    public boolean isPredicate() {
	return isPredicate;
    }

    public void setPredicate() {
	this.isPredicate = true;
    }

    public boolean isString() {
	return isString;
    }

    public void setString() {
	this.isString = true;
    }

}
