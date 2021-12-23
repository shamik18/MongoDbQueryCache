package com.mycomp.cache.token;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KeywordLookup {
    public static final Map<String,ClauseType> KEYWORD_CLAUSE = new HashMap<>();
    public static final Set<String> ComjuctureSet = new HashSet<>();
    public static final Set<String> OperatorSet = new HashSet<>();

    static {
        ComjuctureSet.add("$and");
        ComjuctureSet.add("$or");


        OperatorSet.add("$regex");
        OperatorSet.add("$gt");
        OperatorSet.add("$lt");
    }

    static {
        KEYWORD_CLAUSE.put("$match",ClauseType.WHERE);
        KEYWORD_CLAUSE.put("$group",ClauseType.GROUPBY);
        KEYWORD_CLAUSE.put("$count",ClauseType.GROUPBY);
        KEYWORD_CLAUSE.put("$sort",ClauseType.SORT);
        KEYWORD_CLAUSE.put("$project",ClauseType.SELECT);
    }
}
