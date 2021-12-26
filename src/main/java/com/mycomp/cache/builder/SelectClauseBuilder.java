package com.mycomp.cache.builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.mycomp.cache.clause.Query;
import com.mycomp.cache.clause.SelectClause;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.mycomp.cache.constant.KeywordLookup.FIELD_MAPPER;

public class SelectClauseBuilder {


    public SelectClause populateSelAggrFunct(Query query , Object objectArrgFunct){
        SelectClause selectClause = query.getSelectClause();
        if(selectClause != null){
            selectClause.setAggregateFunction(AggregateFuncGenerator.functionGenerator((JsonNode) objectArrgFunct));
        }
        return selectClause;
    }

    public SelectClause populateSelectClause(Object jsonSelect) {
        SelectClause selectClause = new SelectClause();
        List<String> fields = new ArrayList<>();
        if(jsonSelect instanceof  JsonNode){
            JsonNode node = (JsonNode) jsonSelect;
            if(node.isObject()){
                for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext(); ){
                    Map.Entry<String, JsonNode> entry = it.next();
                    if(entry.getValue().asInt() == 1){
                        fields.add(FIELD_MAPPER.get(entry.getKey()));
                    }
                }
            }
        }
        selectClause.setFields(fields);
//        System.out.println(selectClause);
        return selectClause;
    }

}
