package generation.idtable;

import parse.syntaxtree.NodeAST;

/**
 *  ласс определ€ет предикат. »м€ предиката и его контекст(namespace) однозначно
 * определ€ют его.
 * 
 * @author hindu
 * */
public class Predicate {
    private String name;
    private String namespace;
    private String group;
    private Double priority;
    private NodeAST impl;

    public Predicate(String name, String namespace, String group, Double priority, NodeAST impl) {
	super();
	this.name = name;
	this.namespace = namespace;
	this.group = group;
	this.priority = priority;
	this.impl = impl;
    }

    public Predicate() {

    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getNamespace() {
	return namespace;
    }

    public void setNamespace(String namespace) {
	this.namespace = namespace;
    }

    public String getGroup() {
	return group;
    }

    public void setGroup(String group) {
	this.group = group;
    }

    public Double getPriority() {
	return priority;
    }

    public void setPriority(Double priority) {
	this.priority = priority;
    }

    public NodeAST getImpl() {
	return impl;
    }

    public void setImpl(NodeAST impl) {
	this.impl = impl;
    }
}
