package parse.syntaxtree.nodes;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class SetAST extends NodeAST {
	private List<LiteralAST> elements;
	
	{
		elements = new LinkedList<LiteralAST>();	
	}
	
	public void addElement(LiteralAST element){
		elements.add(element);
		addSuccessor(element);
	}
	
	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub
		
	}

}
