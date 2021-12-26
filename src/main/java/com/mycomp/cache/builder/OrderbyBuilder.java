package com.mycomp.cache.builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.mycomp.cache.clause.OrderByClause;
import com.mycomp.cache.clause.Query;

import java.util.Iterator;
import java.util.Map;

public class OrderbyBuilder {
    public OrderByClause populateOrderByClause(Query query, Object obj) {
        OrderByClause orderByClause = query.getOrderByClause();
        if(orderByClause == null){
            orderByClause = new OrderByClause();
        }
        JsonNode node = null;
        if(obj instanceof JsonNode){
            node = (JsonNode) obj;
        }
        if(node != null){
            if(node.isObject()){
                for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext(); ){
                    Map.Entry<String, JsonNode> entry = it.next();
                    if(entry.getKey().equals("name")){
                        orderByClause.setName(entry.getValue().asText());
                    }else if(entry.getKey().equals("direction") && entry.getValue().asText().equals("desc")){
                        orderByClause.setDirection(1);
                    }
                }
            }
        }
        return orderByClause;
    }
}
