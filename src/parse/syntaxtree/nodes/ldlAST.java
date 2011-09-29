package parse.syntaxtree.nodes;
/**
 * ����� �������� ������ �� �������� ����������� �����: ��������� � �������� ����������.
 * */
import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;
import parse.syntaxtree.TreeWalker;

public class ldlAST extends NodeAST {
	private List<ContextAST> contexts;
	private List<PredicateImplAST> impls;
	
	{
		contexts = new LinkedList<ContextAST>();
		impls = new LinkedList<PredicateImplAST>();
	}
	

	public void addContext(ContextAST context) {
		contexts.add(context);
		addSuccessor(context);
	}

	public void addPredicateImpl(PredicateImplAST impl) {
		impls.add(impl);
		addSuccessor(impl);
	}

	public List<ContextAST> getContexts() {
		return contexts;
	}

	public List<PredicateImplAST> getImpls() {
		return impls;
	}

	@Override
	public void accept(TreeWalker walker) {
		// TODO Auto-generated method stub

	}

}
