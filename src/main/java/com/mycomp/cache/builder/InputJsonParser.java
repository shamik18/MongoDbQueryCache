package com.mycomp.cache.builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mycomp.cache.clause.Query;
import com.mycomp.cache.constant.Constants;
import com.mycomp.cache.constant.KeywordLookup;
import com.mycomp.cache.enums.ClauseType;
import com.mycomp.cache.enums.MethodType;
import com.mycomp.util.JsonUtil;
import com.mycomp.util.StringExtractor;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.mycomp.cache.constant.Constants.*;

public class InputJsonParser {

    public Map<ClauseType,Object> getClause(String jsonQuery, Query query) {
        String cleanString = StringUtils.deleteWhitespace(jsonQuery);
        Map<ClauseType,Object> clauseMap = new HashMap<>();
        if(StringUtils.contains(cleanString,AGGREGATE)){
            query.setMethodType(MethodType.METHOD_AGGREGATE);
            String methodParam = StringExtractor.getMethodParameter(cleanString,AGGREGATE);
            JsonNode node = JsonUtil.getJsonNode(methodParam);
            if(node.isArray()){
                ArrayNode arrayNode = (ArrayNode) node;
                for(int i = 0; i < arrayNode.size(); i++) {
                    JsonNode arrayElement = arrayNode.get(i);
                    for (Iterator<Map.Entry<String, JsonNode>> it = arrayElement.fields(); it.hasNext(); ) {
                        Map.Entry<String, JsonNode> entry = it.next();
                        if(KeywordLookup.KEYWORD_CLAUSE.get(entry.getKey()).equals(ClauseType.WHERE)){
                            clauseMap.put(ClauseType.WHERE,entry.getValue());
                            query.setMongoQuery(entry.getValue().toString());
                        }else if(KeywordLookup.KEYWORD_CLAUSE.get(entry.getKey()).equals(ClauseType.SELECT)){
                            clauseMap.put(ClauseType.SELECT,entry.getValue());
                        }else if(KeywordLookup.KEYWORD_CLAUSE.get(entry.getKey()).equals(ClauseType.GROUPBY)){
                            clauseMap.put(ClauseType.GROUPBY,arrayElement);
                        }else if(KeywordLookup.KEYWORD_CLAUSE.get(entry.getKey()).equals(ClauseType.ARRG_FUNC)){
                            clauseMap.put(ClauseType.ARRG_FUNC,arrayElement);
                        }else if(KeywordLookup.KEYWORD_CLAUSE.get(entry.getKey()).equals(ClauseType.SORT)){
                            clauseMap.put(ClauseType.SORT,arrayElement);
                        }
                    }
                }
            }
        }else if(StringUtils.contains(cleanString,FIND)){
            query.setMethodType(MethodType.METHOD_FIND);
            String[] strings = StringUtils.split(cleanString,SEPARATOR_DOT);

            for(String token : strings){
                if(token.contains(FIND)){

                    extractJsonFromFind(token,clauseMap);
                }else if(token.contains(SORT)){
                    extractJsonFromSort(token,clauseMap);
                }else if(token.contains(LIMIT)){
                    extractJsonFromLimit(token,clauseMap);
                }
            }
        }
//        System.out.println(clauseMap);
        return clauseMap;
    }
    private static void extractJsonFromLimit(String token, Map<ClauseType, Object> clauseMap) {
        String methodParam = StringExtractor.getMethodParameter(token, LIMIT);
        clauseMap.put(ClauseType.LIMIT,methodParam);
    }

    private static void extractJsonFromSort(String token, Map<ClauseType, Object> clauseMap) {
        String methodParam = StringExtractor.getMethodParameter(token, SORT);
        System.out.println(methodParam);
        String[] tokens= StringUtils.split(methodParam,",");
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name",StringUtils.remove(tokens[0],"\""));
        if(tokens[1].equals("-1")){
            objectNode.put("direction","desc");
        }else {
            objectNode.put("direction","asc");
        }

        clauseMap.put(ClauseType.SORT,objectNode);
    }

    private static void extractJsonFromFind(String token,Map<ClauseType,Object> clauseMap ) {
        String methodParam = StringExtractor.getMethodParameter(token,FIND);
        methodParam = "["+methodParam+"]";
        JsonNode node = JsonUtil.getJsonNode(methodParam);
        ClauseType[] clauseTypes = new ClauseType[]{ClauseType.WHERE,ClauseType.SELECT};
        if(node.isArray()){
            ArrayNode arrayNode = (ArrayNode) node;
            for(int i = 0; i < arrayNode.size(); i++) {
                JsonNode arrayElement = arrayNode.get(i);
//                System.out.println(arrayElement);
                clauseMap.put(clauseTypes[i],arrayElement);
            }
        }
    }
}
