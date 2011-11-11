package parse.syntaxtree;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

/** Общий класс для всех элементов дерева. 
 * Дает возможность обхода дерева.
 * */
public abstract class NodeAST extends NodeProperty{
	private NodeAST parent;
	// FUU List изменен на LinkedList;
	private LinkedList<NodeAST> successors;
	
	{
		successors = new LinkedList<NodeAST>();
	}
	
	
	public abstract void accept(TreeWalker walker);

	public NodeAST getParent() {
		return parent;
	}

	public void setParent(NodeAST parent) {
		this.parent = parent;
	}
	
	public void addSuccessor(NodeAST node){
		successors.add(node);
	}
	
	public void addSuccessos(List<NodeAST> nodes){
		successors.addAll(nodes);
	}
	
	public LinkedList<NodeAST> getSuccessors(){
		return successors;
	}
}
