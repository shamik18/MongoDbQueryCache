package com.mycomp.cache.builder;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

public class AggregateFuncGenerator {
    private static Map<String,String> aggr_lookup = new HashMap<>();
    static {
        String str = "{\"$count\":\"number_of_records\"}";
        aggr_lookup.put("{\"$count\":\"number_of_records\"}","count(*) ");
    }
    public static String functionGenerator(JsonNode jsonNode){
        if(jsonNode != null){
            return aggr_lookup.get(jsonNode.toString());
        }
       return null;
    }
}
