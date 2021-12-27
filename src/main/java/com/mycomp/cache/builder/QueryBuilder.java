package com.mycomp.cache.builder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mchange.lang.IntegerUtils;
import com.mycomp.cache.clause.*;
import com.mycomp.cache.constant.KeywordLookup;
import com.mycomp.cache.enums.ClauseType;
import com.mycomp.cache.enums.MethodType;
import com.mycomp.cache.token.*;
import com.mycomp.cache.token.primitive.BooleanOperand;
import com.mycomp.cache.token.primitive.IntOperand;
import com.mycomp.cache.token.primitive.StringOperand;
import com.mycomp.util.JsonUtil;
import com.mycomp.util.StringExtractor;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;

import static com.mycomp.cache.constant.KeywordLookup.*;

public class QueryBuilder {
    private GroupByClauseBuilder groupByClauseBuilder = new GroupByClauseBuilder();
    private WhereClauseBuilder whereClauseBuilder = new WhereClauseBuilder();
    private SelectClauseBuilder selectClauseBuilder = new SelectClauseBuilder();
    private OrderbyBuilder orderbyBuilder = new OrderbyBuilder();
    private RecLimitBuilder recLimitBuilder = new RecLimitBuilder();
    private InputJsonParser inputJsonParser = new InputJsonParser();

    public Query populateQuery(String jsonQuery){
        Query query = new Query();
        Map<ClauseType,Object> map = inputJsonParser.getClause(jsonQuery, query);
        query.setSelectClause(selectClauseBuilder.populateSelectClause(map.get(ClauseType.SELECT)));
        query.setSelectClause(selectClauseBuilder.populateSelAggrFunct(query,map.get(ClauseType.ARRG_FUNC)));
        query.setFromClause(populateFromClause());
        query.setWhereClause(whereClauseBuilder.populateWhereClause(map.get(ClauseType.WHERE)));
        query.setGroupByClause(groupByClauseBuilder.populateGrpByClause(query,map.get(ClauseType.GROUPBY)));
        query.setOrderByClause(orderbyBuilder.populateOrderByClause(query,map.get(ClauseType.SORT)));
        query.setRecLimit(recLimitBuilder.populateRecLimit(query,map.get(ClauseType.LIMIT)));
        List<JsonNode> jsonNodes = (List<JsonNode>) map.get(ClauseType.WHERE);


        if(jsonNodes.size() == 1){
            query.setMongoQuery(jsonNodes.get(0).toString());
        }else{
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode arrayNode = mapper.createArrayNode();
            ObjectNode andNode = mapper.createObjectNode();
            andNode.set("$and",arrayNode);
            for(JsonNode jsonNode: jsonNodes){
                arrayNode.add(jsonNode);
            }
            query.setMongoQuery(andNode.toString());
        }


//        System.out.println(query);
        return query;
    }

    private FromClause populateFromClause() {
        FromClause fromClause = new FromClause();
        String from = "from HomeProperty hp";
        fromClause.setFrom(from);
//        System.out.println(fromClause);
        return fromClause;
    }
}
