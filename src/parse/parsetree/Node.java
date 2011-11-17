package parse.parsetree;

import java.util.ArrayList;
import java.util.List;

import parse.syntaxtree.NodeAST;

/**
 * Общий класс для всех элементов дерева разбора. Обеспечивает обход дерева.
 *
 * @author hindu
 * */
public abstract class Node {
    private Node parent;
    private ArrayList<Node> succsessors = new ArrayList<Node>();

    public ArrayList<Node> getSuccsessors() {
	return succsessors;
    }

    public Node getSuccsessor() {
	return getSuccsessor(0);
    }

    public Node getSuccsessor(int index) {
	return succsessors.get(index);
    }

    public Node getParent() {
	return parent;
    }

    // автоматически добавляет обратную связь
    public void setParent(Node parent) {
	this.parent = parent;
	parent.addChild(this);
    }

    private void addChild(Node child) {
	succsessors.add(child);
    }

    /**
     * Метод разворачивает дерево блоков в список, например правило Blocks :
     * Block Blocks : Block Blocks преобразуется в Blocks : Block Block ...
     * Block
     * */
    public void makeLinearList(List<Node> nodes) {
	nodes.add(this.getSuccsessors().get(0));

	if (this.getSuccsessors().size() == 2) {
	    (this.getSuccsessors().get(1)).makeLinearList(nodes);
	}
    }

    public abstract NodeAST getConvertedSubtree();
}
