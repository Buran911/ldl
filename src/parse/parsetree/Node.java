/** Общий класс для всех элементов дерева разбора.
* Обеспечивает обход дерева.
*/
package parse.parsetree;

import java.util.ArrayList;

public abstract class Node{
	private Node parent;
	private ArrayList< Node > succsessors  =  new ArrayList< Node >();
	
	public ArrayList<Node> getSuccsessors() {
		return succsessors;
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
