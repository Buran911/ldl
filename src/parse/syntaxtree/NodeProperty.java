package parse.syntaxtree;

import java.io.Serializable;

/**
 * Класс содержит в себе типы узлов АСТ. Необходимо шаблонизатору.
 * @author hindu
 * */
public class NodeProperty implements Serializable {
    /**
     * @see NodeTypeVisitor булевы значения isNode, isLeaf, etc устанавливаются в
     *      этом классе.
     */
    private boolean isNode; // true, если у объекта есть "дети" и будет
			    // необходимо их разбирать
    private boolean isLeaf; // true, если это лист
    private boolean isResultDefenition; // true, если объект типа
					// AbstractResultDefenitionAST
    private boolean isAttributeCallExp; // true, если объект типа
					// AttributeCallExprssionAST
    private boolean isVariableExp; // true, если объект типа VariableExpAST
    private boolean isBoolExp; // true, если это вызов функции includes
    private boolean isDummy; // true, если это "пустышка" и дальше идти не надо
    private boolean isFunction; // true, если это вызов функции
    private boolean isPredicate; // true, если это вызов предиката
    private boolean isString; // true, если это строковый тип данных

    private boolean used; // true, ставиться при необходимости, если элемент
			  // дерева уже обработан

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
