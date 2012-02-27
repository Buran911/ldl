package generation.idtable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс содержит в себе таблицу идентификаторов(идентификаторы и предикаты) а так же методы доступа к ним.
 * @author hindu
 * */
public class IdTable {
    private List<Identifier> idTable;
    private List<Predicate> predicateTable;
    private Integer idIndex;

    {
	idTable = new LinkedList<Identifier>();
	predicateTable = new LinkedList<Predicate>();
	idIndex = 0;
    }

    public void addId(Identifier id) {
	id.setIndex(idIndex);
	id.makeAlias();
	idIndex++;

	idTable.add(id);
    }

    public void addPredicate(Predicate predicate) {
	predicateTable.add(predicate);
    }

    public List<Identifier> getIds() {
	return idTable;
    }

    public Identifier getId(String alias) {
	for (Identifier id : idTable) {
	    if (id.getAlias().contentEquals(alias)) {
		return id;
	    }
	}

	return null;
    }

    public Identifier getId(String name, String namespace) {
	List<Identifier> ids = getIdByNamespace(namespace);

	for (Identifier id : ids) {
	    if (id.getName().contentEquals(name)) {
		return id;
	    }
	}

	return null;
    }

    public List<Identifier> getIdByNamespace(String namespace) {
	List<Identifier> identifiers = new ArrayList<Identifier>();
	for (Identifier identifier : idTable) {
	    if (identifier.getNamespace().contentEquals(namespace)) {
		identifiers.add(identifier);
	    }
	}

	return identifiers;
    }

    public Predicate getPredicate(String name, String namespace) {
	List<Predicate> predicates = getPredicateByNamespace(namespace);

	for (Predicate predicate : predicates) {
	    if (predicate.getName().contentEquals(name)) {
		return predicate;
	    }
	}

	return null;
    }

    public List<Predicate> getPredicateByNamespace(String namespace) {
	List<Predicate> predicates = new ArrayList<Predicate>();
	for (Predicate predicate : predicateTable) {
	    if (predicate.getNamespace().contentEquals(namespace)) {
		predicates.add(predicate);
	    }
	}

	return predicates;
    }

}
