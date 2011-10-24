/** Общий класс для всех элементов дерева разбора.
* Обеспечивает обход дерева.
*/
package parse.parsetree;

import java.util.ArrayList;
import java.util.List;

import parse.parsetree.nodes.ldlExpressions;
import parse.syntaxtree.NodeAST;


public abstract class Node{
	private Node parent;
	private ArrayList< Node > succsessors  =  new ArrayList< Node >();
	
	public ArrayList<Node> getSuccsessors() {
		return succsessors;
	}
	
	public Node getSuccsessor(){
		return getSuccsessor(0);
	}

	public Node getSuccsessor(int index) {
		return succsessors.get(index);
	}

	public Node getParent() {
		return parent;
	}
	
	// автоматически добавляет обратную связь
	public void setParent( Node parent) {
		this.parent = parent;
		parent.addChild( this );
	}
	
	private void addChild( Node child ){
		succsessors.add( child );
	}
	
	/** Метод разворачивает дерево блоков в список, например правило
	 * Blocks : Block
	 * Blocks : Block Blocks 
	 * преобразуется в 
	 * Blocks : Block Block ... Block
	 * */
	public void makeLinearList(List<Node> nodes){
		nodes.add(this.getSuccsessors().get(0));
		
		if( this.getSuccsessors().size() == 2){
			(this.getSuccsessors().get(1)).makeLinearList(nodes);
		}
	}
	
	public abstract NodeAST getConvertedSubtree();
	
	public boolean simpleEqual( Node node ){
		if (succsessors.size() != node.succsessors.size()){
			return false;
		}
		
		for (int i = 0; i < succsessors.size(); i++){
			Node its = succsessors.get(i);
			Node theirs = node.succsessors.get(i);
			if (!its.getClass().getName().equals(theirs.getClass().getName())){
				return false;
			}
			if( !its.simpleEqual(theirs)){
				return false;
			}
		}
		return true;
	}
	
//	public abstract ASTNode getConvertedSubtree( );
	
}
