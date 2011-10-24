package parse.syntaxtree.nodes;

import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;

public class SourceAST extends NodeAST {
	private List<srcBlockAST> srcBlocks;
	
	{
		srcBlocks = new LinkedList<srcBlockAST>();
	}
	
	public void addSrcBlock(srcBlockAST block){
		srcBlocks.add(block);
		addSuccessor(block);
	}
	
	public List<srcBlockAST> getSrcBlocks() {
		return srcBlocks;
	}

	@Override
	public void accept(TreeWalker walker) {
		walker.accept(this);

	}

}
