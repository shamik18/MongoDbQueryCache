package com.mycomp.cache.builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.mycomp.cache.clause.GroupByClause;
import com.mycomp.cache.clause.Query;
import com.mycomp.cache.clause.SelectClause;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static com.mycomp.cache.constant.KeywordLookup.FIELD_MAPPER;

public class GroupByClauseBuilder {
    public GroupByClause populateGrpByClause(Query query, Object jsonAggrFunct) {
        GroupByClause groupByClause = query.getGroupByClause()!=null?query.getGroupByClause():new GroupByClause();
        SelectClause selectClause = query.getSelectClause()!=null?query.getSelectClause():new SelectClause();
        boolean groupByInd = false;
        if(jsonAggrFunct instanceof JsonNode){
            JsonNode node = (JsonNode) jsonAggrFunct;
            if(node.isObject()){
                node = node.get("$group");
                for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext(); ) {
                    Map.Entry<String,JsonNode> entry = it.next();
                    Set<String> key= new HashSet<>();
                    if(entry.getKey().equals("_id")){
                        if(entry.getValue().isObject()){
                            entry.getValue().fields().forEachRemaining(e -> {
                                String field = FIELD_MAPPER.get(StringUtils.strip(e.getValue().asText(),"$"));
                                key.add(field);
                            });
                        }
                        groupByClause.setGroupByField(key);
                        groupByClause.setApply(true);
                        groupByInd = true;
                        groupByClause.setGroupByInd(groupByInd);
                    }else {
                        String alias = entry.getKey();
                        Map<String,Object> aggeragate = new HashMap<>();
                        entry.getValue().fields().forEachRemaining(e -> {
                            if(e.getValue().isInt()){
                                aggeragate.put(e.getKey(),e.getValue().asInt());
                            }else if(e.getValue().isTextual()){
                                aggeragate.put(e.getKey(),e.getValue().asText());
                            }
                        });
                        String aggrFunct="";
                        for(String funct: aggeragate.keySet()){
                            String methodName = StringUtils.strip(funct,"$");
                            String fieldName = FIELD_MAPPER.get(aggeragate.get(funct))!=null? FIELD_MAPPER.get(aggeragate.get(funct)): aggeragate.get(funct)+"";
                            aggrFunct = methodName+"("+fieldName+")"+" as "+alias;
                        }
                        selectClause.setAggregateFunction(aggrFunct);
                        selectClause.setAggregate(true && !groupByInd);
                        query.setSelectClause(selectClause);
                    }
                }
            }
        }

//        System.out.println(jsonAggrFunct);
        return groupByClause;
    }
}
