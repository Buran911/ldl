package parse.syntaxtree;

import parse.parsetree.ParseTree;

public class SyntaxTree {
	private NodeAST root;

	public SyntaxTree(ParseTree tree) {
		root = tree.getAST();
		prepare();
	}

	/** �������������� ������ � ������, ���������� �������
	 *  � ���������� ����������� ��������*/
	private void prepare() {
		//root.accept( new NodeTypeVisitor());
	}
	
	
}
