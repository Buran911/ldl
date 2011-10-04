package generation.idtable;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parse.syntaxtree.NodeAST;


public class IdTable {
	private Map<Identifier, NodeAST> idTable;
	private Map<Predicate, NodeAST> predicateTable;
	
	private Integer idIndex;
	
	{
		idTable = new HashMap<Identifier, NodeAST>();
		predicateTable = new HashMap<Predicate, NodeAST>();
		idIndex = 1;
	}
	
	{
		idTable = new HashMap<Identifier, NodeAST>();
		predicateTable = new HashMap<Predicate, NodeAST>();
	}
	
	public void addId(Identifier id, NodeAST node){
		id.setIndex(idIndex);
		idIndex++;
		
		idTable.put(id, node);
	}
	
	public void addPredicate(Predicate predicate, NodeAST node){
		predicateTable.put(predicate, node);
	}
	
	public Set<Identifier> getIds(){
		return idTable.keySet();
	}
	
	public Identifier getId(String name, String namespace){
		List<Identifier> ids = getIdByNamespace(namespace);
		
		for(Identifier id : ids){
			if(id.getName().contentEquals(name)){
				return id;
			}
		}
		
		return null;
	}
	
	public List<Identifier> getIdByNamespace( String namespace ){
		List<Identifier> identifiers = new ArrayList<Identifier>();
		for (Identifier identifier : idTable.keySet()){
			if( identifier.getNamespace().contentEquals(namespace)){
				identifiers.add(identifier);
			}
		}
		
		return identifiers;
	}
	
	
}
