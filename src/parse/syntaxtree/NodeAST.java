package parse.syntaxtree;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

/** ����� ����� ��� ���� ��������� ������. 
 * ���� ����������� ������ ������.
 * */
public abstract class NodeAST extends NodeProperty{
	private NodeAST parent;
	private List<NodeAST> successors;
	
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
	
	public List<NodeAST> getSuccessors(){
		return successors;
	}
}
