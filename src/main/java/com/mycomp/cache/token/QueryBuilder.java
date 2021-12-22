package com.mycomp.cache.token;

import java.util.HashMap;
import java.util.Map;

public class QueryBuilder {
    public Query populateQuery(String jsonQuery){
        Query query = new Query();
        Map<String,String> map = getJsonPart(jsonQuery);
        query.setSelectClause(populateSelectClause(map.get("select")));
        query.setFromClause(populateFromClause());
        query.setWhereClause(populateWhereClause(map.get("where")));
        query.setGroupByClause(populateGrpByClause(map.get("groupBy")));

        return query;

    }

    private GroupByClause populateGrpByClause(String groupBy) {
        GroupByClause groupByClause = new GroupByClause();
        return groupByClause;
    }

    private FromClause populateFromClause() {
        FromClause fromClause = new FromClause();
        return fromClause;
    }

    private WhereClause populateWhereClause(String where) {
        WhereClause whereClause = new WhereClause();
        return whereClause;
    }

    private SelectClause populateSelectClause(String select) {
        SelectClause selectClause = new SelectClause();
        return selectClause;
    }

    private Map<String, String> getJsonPart(String jsonQuery) {
        Map<String,String> map = new HashMap<>();
        return map;
    }
}
