package generation.idtable;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.nodes.LiteralAST;

public class Set extends SourceData {
    private String alias;
    private String name;
    private List<LiteralAST> elements;

    {
	elements = new LinkedList<LiteralAST>();
    }

    @Override
    public Object clone() {
	// TODO Auto-generated method stub
	return null;
    }

    public void addElements(LiteralAST element) {
	elements.add(element);
    }

    public List<LiteralAST> getElements() {
        return elements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
