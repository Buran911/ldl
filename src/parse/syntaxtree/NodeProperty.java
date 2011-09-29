package parse.syntaxtree;


public class NodeProperty {
	/**@see  NodeTypeVisitor
	 * булевы значения isNode, isLeaf, etc устанавливаются в этом классе. */
	private boolean isNode; // true, если у объекта есть "дети" и будет необходимо их разбирать
	private boolean isLeaf; // true, если это лист
	private boolean isResultDefenition; //true, если объект типа AbstractResultDefenitionAST
	private boolean isAttributeCallExp; // true, если объект типа AttributeCallExprssionAST
	private boolean isVariableExp; // true, если объект типа VariableExpAST
	private boolean isSubstrCall; // true, если объект типа SubstringCallAST
	private boolean isRightBound; // true, если объект типа RighBoundAST
	private boolean isLeftBound; // true, если объект типа LeftBoundAST
	private boolean isPredefinedFunction; // true, если это системная функция
	private boolean isSubstrFunction; // true, если это вызово подстроки функция
	private boolean isSizeFunction; // true, если это вызов функции размера строки
	private boolean isConcatFunction; // true, если это вызов функции объеденяющей строки
	private boolean isArithmeticExp; // true, если это вызов арфимитическое выражение в LDL
	private boolean isIncludesFunction; // true, если это вызов функции includes
	private boolean isBoolExp; // true, если это вызов функции includes
	
	private boolean used;  // true, ставиться при необходимости, если элемент дерева уже обработан
	
	{
		this.isNode = false;
		this.isLeaf = false;
		this.isResultDefenition = false;
		this.isAttributeCallExp = false;
		this.isVariableExp = false;
		this.isSubstrCall = false;
		this.isRightBound = false;
		this.isLeftBound = false;
		this.isPredefinedFunction = false;
		this.isSubstrFunction = false;
		this.isSizeFunction = false;
		this.isConcatFunction = false;
		this.isArithmeticExp = false;
		this.isIncludesFunction = false;
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
	public boolean isIncludesFunction() {
		return isIncludesFunction;
	}
	public void setIncludesFunction() {
		this.isIncludesFunction = true;
	}
	public boolean isArithmeticExp() {
		return isArithmeticExp;
	}
	public void setArihmeticExp() {
		this.isArithmeticExp = true;
	}
	public boolean isConcatFunction() {
		return isConcatFunction;
	}
	public void setConcatFunction() {
		this.isConcatFunction = true;
	}
	public boolean isSizeFunction() {
		return isSizeFunction;
	}
	public void setSizeFunction() {
		this.isSizeFunction = true;
	}
	public boolean isSubstrFunction() {
		return isSubstrFunction;
	}
	public void setSubstrFunction() {
		this.isSubstrFunction = true;
	}
	public boolean isPredefinedFunction() {
		return isPredefinedFunction;
	}
	public void setPredefinedFunction() {
		this.isPredefinedFunction = true;
	}
	public boolean isSubstrCall() {
		return isSubstrCall;
	}
	public void setSubstrCall() {
		this.isSubstrCall = true;
	}
	public boolean isRightBound() {
		return isRightBound;
	}
	public void setRightBound() {
		this.isRightBound = true;
	}
	public boolean isLeftBound() {
		return isLeftBound;
	}
	public void setLeftBound() {
		this.isLeftBound = true;
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
	
}
