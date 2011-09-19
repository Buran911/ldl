package parse.parsetree;

import java.util.Collections;
import java.util.LinkedList;


public class ParseTree {
	private Node root;
	private LinkedList< Node > nodes = new LinkedList< Node >();
	
	public ParseTree getTree(){
		return this;
	}
	
	public void setRoot(Node root) {
		this.root =  root;
	}
	
	public Node getRoot() {
		return root;
	}

	public void saveNode( Node node ){
		nodes.add( node );
	}
	
	public Node getLast(){
		return nodes.getLast();
	}
	
	public void reverseSuccsessors(){
		reverseSuccsessors(root);
	}
	
	private void reverseSuccsessors(Node node){
		Collections.reverse(node.getSuccsessors());
		for (Node child : node.getSuccsessors()){
			reverseSuccsessors(child);
		}
	}
	
	
	public void printTree(){
		printSubTree( root, 0 );
	}

	public void printSubTree(Node node, int level){
		String className = node.getClass().getName(); 
		// ќтрезаем названи€ пакетов за ненадобностью
		className = className.substring(className.lastIndexOf('.') + 1);
		System.out.println( format(className, level));
		
		for( Node succsessor : node.getSuccsessors()){
			printSubTree(succsessor, level + 1);
		}
	}
	
	private String format( String string, int level ){
		String tabStr = "  ";
		StringBuilder sb = new StringBuilder();
		for( int i = 0; i < level; i++){
			sb.append(tabStr);
		}
		
		return sb.toString() + string;
	}
}
