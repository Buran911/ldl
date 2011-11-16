package generation.idtable;

import generation.languageconstants.Type;

public class Identifier {
    private String name;
    private String namespace;
    private String type;
    private Type srcType;
    private SourceData srcData;
    private Boolean visible;

    private Integer index;
    private String alias;

    // ��������� ��������
    {
	visible = true;
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

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public Type getSrcType() {
	return srcType;
    }

    public void setSrcType(Type srcType) {
	this.srcType = srcType;
    }

    public SourceData getSrcData() {
	return srcData;
    }

    public void setSrcData(SourceData srcData) {
	this.srcData = srcData;
    }

    public Integer getIndex() {
	return index;
    }

    public void setIndex(Integer index) {
	this.index = index;
    }

    public void makeAlias() {
	alias = name.toUpperCase() + "_" + index.toString();
    }

    public String getAlias() {
	return alias;
    }

    public Boolean isVisible() {
	return visible;
    }

    public void setVisible(Boolean visible) {
	this.visible = visible;
    }
    public Identifier clone(){
	Identifier copy = new Identifier();
	copy.name = name;

	copy.namespace = namespace;
	copy.type = type;
	copy.srcType = srcType;
	copy.srcData = srcData.clone();
	copy.visible = visible;
	
	return copy;
    }
}
