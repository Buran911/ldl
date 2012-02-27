package application.util;

import generation.db.QueryResult;

public interface Filterable {
    public void filter (QueryResult queryResult);
}
