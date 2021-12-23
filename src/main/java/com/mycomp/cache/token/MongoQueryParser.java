package com.mycomp.cache.token;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mycomp.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;

public class MongoQueryParser {
//    private static final Set<String> ComjuctureSet = new HashSet<>();
//    private static final Set<String> OperatorSet = new HashSet<>();
//
//    static {
//        ComjuctureSet.add("$and");
//        ComjuctureSet.add("$or");
//
//
//        OperatorSet.add("$regex");
//        OperatorSet.add("$gt");
//        OperatorSet.add("$lt");
//    }
//
//    public WhereClause whereClauseGenerator(String query){
//        Stack<Object> stack = new Stack<>();
//        JsonParser jsonParser = JsonUtil.getJsonParser(query);
//        while (!jsonParser.isClosed()){
//            try {
//                JsonToken jsonToken = jsonParser.nextToken();
//                if(JsonToken.START_OBJECT.equals(jsonToken)){
//                    stack.push(JsonToken.START_OBJECT);
//                }else if(JsonToken.FIELD_NAME.equals(jsonToken)){
//                    String name = jsonParser.currentName();
//                    if(ComjuctureSet.contains(name)){
//                        Constrain constrain = new Constrain();
//                        constrain.setConstrain_name(name);
//                        stack.push(constrain);
//                    }else if(OperatorSet.contains(name)){
//                        Operands operands = new Operands();
//                        operands.setOperator(name);
//                        stack.push(operands);
//                    }else {
//                        Attribute attribute = new Attribute();
//                        attribute.setAttr_name(name);
//                        stack.push(attribute);
//                    }
//                }else if(JsonToken.VALUE_STRING.equals(jsonToken)){
//                    String s = jsonParser.getValueAsString();
//                    stack.push(s);
//                }else if(JsonToken.VALUE_NUMBER_INT.equals(jsonToken)){
//                    Integer integer = jsonParser.getValueAsInt();
//                    stack.push(integer);
//                }else if(JsonToken.VALUE_NUMBER_FLOAT.equals(jsonToken)){
//                    Double db = jsonParser.getValueAsDouble();
//                    stack.push(db);
//                }else if(JsonToken.VALUE_TRUE.equals(jsonToken) || JsonToken.VALUE_FALSE.equals(jsonToken)){
//                    Boolean boolVal = jsonParser.getValueAsBoolean();
//                    stack.push(boolVal);
//                }else if(JsonToken.END_OBJECT.equals(jsonToken)){
//                    Object previousValue = null;
//                    while (true){
//                        Object elem = stack.pop();
//                        if(elem instanceof  JsonToken ){
//                            JsonToken tokenElem = (JsonToken) elem;
//                            if(tokenElem.equals(JsonToken.START_OBJECT)){
//                                stack.push(previousValue);
//                                break;
//                            }
//                        }else if(elem instanceof Attribute){
//                            Attribute attribute = (Attribute) elem;
//                           if(previousValue != null && previousValue instanceof  String){
//                                StringOperand stringOperand = new StringOperand();
//                                stringOperand.setValue((String) previousValue);
//                               Set<Operands> ops = new HashSet<>();
//                               ops.add(stringOperand);
//                               attribute.setOperandsSet(ops);
//                            }else if(previousValue != null && previousValue instanceof  Integer){
//                                IntOperand intOperand = new IntOperand();
//                                intOperand.setValue((Integer) previousValue);
//                               Set<Operands> ops = new HashSet<>();
//                               ops.add(intOperand);
//                               attribute.setOperandsSet(ops);
//                            }else if(previousValue != null && previousValue instanceof  Boolean) {
//                               BooleanOperand booleanOperand = new BooleanOperand();
//                               booleanOperand.setValue((Boolean) previousValue);
//                               Set<Operands> ops = new HashSet<>();
//                               ops.add(booleanOperand);
//                               attribute.setOperandsSet(ops);
//                            }else if(previousValue != null && previousValue instanceof  Operands) {
//                               Operands operands = (Operands) previousValue;
//                               Set<Operands> ops = new HashSet<>();
//                               ops.add(operands);
//                               attribute.setOperandsSet(ops);
//                           }
//                           previousValue = attribute;
//                        }else if(elem instanceof Operands){
//                            Operands operands = (Operands) elem;
//                            if(previousValue != null && previousValue instanceof  String){
//                                StringOperand stringOperand = new StringOperand();
//                                stringOperand.setOperator(operands.getOperator());
//                                stringOperand.setValue((String) previousValue);
//                                operands = stringOperand;
//                            }else if(previousValue != null && previousValue instanceof  Integer){
//                                IntOperand intOperand = new IntOperand();
//                                intOperand.setOperator(operands.getOperator());
//                                intOperand.setValue((Integer) previousValue);
//                                operands = intOperand;
//                            }else if(previousValue != null && previousValue instanceof  Boolean) {
//                                BooleanOperand booleanOperand = new BooleanOperand();
//                                booleanOperand.setOperator(operands.getOperator());
//                                booleanOperand.setValue((Boolean) previousValue);
//                                operands = booleanOperand;
//                            }
//                            previousValue = operands;
//                        }else if(elem == null || elem instanceof String || elem instanceof Integer || elem instanceof Boolean ){
//                            if(elem == null){
//                                previousValue = Optional.empty();
//                            }else {
//                                previousValue = elem;
//                            }
//                        }else if(elem instanceof Set){
//                            previousValue = elem;
//                        }else if(elem instanceof Constrain) {
//                            Constrain constrain = (Constrain) elem;
//                            if(previousValue instanceof  Set){
//                                Set set = (Set) previousValue;
//                                Object obj = set.stream().findFirst().get();
//                                if(obj instanceof  Attribute){
//                                    constrain.setAttributes(set);
//                                }else if (obj instanceof Constrain){
//                                    constrain.setChildConstains(set);
//                                }
//                            }else if(previousValue instanceof Constrain){
//                                Set<Constrain> constrainSet = new HashSet<>();
//                                constrainSet.add((Constrain)previousValue);
//                            }
//                            previousValue = constrain;
//                        }
//                    }
//                }else if(JsonToken.START_ARRAY.equals(jsonToken)){
//                    stack.push(jsonToken);
//                }else if(JsonToken.END_ARRAY.equals(jsonToken)){
//                    Set<Attribute> attributeSet = new HashSet<>();
//                    while (true){
//                        Object elem = stack.pop();
//                        if(elem instanceof  JsonToken ){
//                            JsonToken tokenElem = (JsonToken) elem;
//                            if(tokenElem.equals(JsonToken.START_ARRAY)){
//                                stack.push(attributeSet);
//                                break;
//                            }
//                        }else if (elem instanceof Attribute){
//                            attributeSet.add((Attribute) elem);
//                        }
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//         WhereClause whereClause = new WhereClause();
//        if(stack.size() == 1){
//            Object ob = stack.pop();
//            if(ob instanceof Constrain){
//                whereClause.setConstrain((Constrain) ob);
//            }else if(ob instanceof  Attribute){
//                whereClause.setAttribute((Attribute) ob);
//            }
//        }
//        return whereClause;
//    }
//
//    public static void main(String[] args) {
//        String str1 = "{ \"$or\" : [ {\"name\":{\"$regex\":\"Beach\"} } , { \"property_type\":\"House\"} ] }";
//        MongoQueryParser mongoQueryParser = new MongoQueryParser();
//        WhereClause whereClause = mongoQueryParser.whereClauseGenerator(str1);
//        String str2 = "{ \"$or\" : [ {\"name\":{\"$regex\":\"Beach\"} } ] }";
//        WhereClause whereClause2 = mongoQueryParser.whereClauseGenerator(str2);
//        String str3 = "collection.aggregate([ \n" +
//                "                {\n" +
//                "                        \"$match\" : {\n" +
//                "                                        \"name\": {\"$regex\":\"Beach\"}, \n" +
//                "                                        \"property_type\": {\"$exists\":true, \"$eq\": \"House\"}, \n" +
//                "                                        \"accommodates\": {\"$gt\": 6 }\n" +
//                "                                        \n" +
//                "                        }\n" +
//                "\n" +
//                "                }, \n" +
//                "                {        \n" +
//                "                        \"$count\":  \"number_of_records\"  \n" +
//                "                }\n" +
//                "                 \n" +
//                "        ])";
//
//        String str4 = "collection.find( { \"$or\" : [ {\"name\":{\"$regex\":\"Beach\"} } , { \"property_type\":\"House\"} ] }, {\"name\":1,\"_id\":0} ).sort(\"name\",-1).limit(10)";
//        String cleanString = StringUtils.deleteWhitespace(str3);
//        System.out.println(cleanString);
//        Map<ClauseType,Object> clauseMap = new HashMap<>();
//        if(StringUtils.contains(cleanString,"aggregate")){
//            String methodParam =StringUtils.substringAfter(cleanString,"aggregate");
//            methodParam = StringUtils.substringBetween(methodParam,"(",")");
//            JsonNode node = JsonUtil.getJsonNode(methodParam);
//            if(node.isArray()){
//                ArrayNode arrayNode = (ArrayNode) node;
//                for(int i = 0; i < arrayNode.size(); i++) {
//                    JsonNode arrayElement = arrayNode.get(i);
//                    System.out.println(arrayElement);
//                    System.out.println(arrayElement);
//                    for (Iterator<Map.Entry<String, JsonNode>> it = arrayElement.fields(); it.hasNext(); ) {
//                        Map.Entry<String, JsonNode> entry = it.next();
//                        if(KeywordLookup.KEYWORD_CLAUSE.get(entry.getKey()).equals(ClauseType.WHERE)){
//                            clauseMap.put(ClauseType.WHERE,entry.getValue());
//                        }else if(KeywordLookup.KEYWORD_CLAUSE.get(entry.getKey()).equals(ClauseType.SELECT)){
//                            clauseMap.put(ClauseType.SELECT,entry.getValue());
//                        }else if(KeywordLookup.KEYWORD_CLAUSE.get(entry.getKey()).equals(ClauseType.GROUPBY)){
//                            clauseMap.put(ClauseType.GROUPBY,arrayElement);
//                        }else if(KeywordLookup.KEYWORD_CLAUSE.get(entry.getKey()).equals(ClauseType.GROUPBY)){
//                            clauseMap.put(ClauseType.GROUPBY,arrayElement);
//                        }
//                    }
//                }
//            }
//        }else if(StringUtils.contains(cleanString,"find")){
//            String[] strings = StringUtils.split(cleanString,".");
//
//            for(String token : strings){
//                if(token.contains("find")){
//                    extractJsonFromFind(token,clauseMap);
//                }else if(token.contains("sort")){
//                    extractJsonFromSort(token,clauseMap);
//                }else if(token.contains("limit")){
//                    extractJsonFromLimit(token,clauseMap);
//                }
//            }
//        }
//
//        System.out.println(clauseMap);
//
//
//
//    }
//
//    private static void extractJsonFromLimit(String token, Map<ClauseType, Object> clauseMap) {
//        String methodParam = StringUtils.substringBetween(StringUtils.substringAfter(token,"limit"),"(",")");
//        clauseMap.put(ClauseType.LIMIT,methodParam);
//    }
//
//    private static void extractJsonFromSort(String token, Map<ClauseType, Object> clauseMap) {
//        String methodParam = StringUtils.substringBetween(StringUtils.substringAfter(token,"sort"),"(",")");
//        System.out.println(methodParam);
//        String[] tokens= StringUtils.split(methodParam,",");
//        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectNode objectNode = objectMapper.createObjectNode();
//        objectNode.put("name",StringUtils.remove(tokens[0],"\""));
//        if(tokens[1].equals("-1")){
//            objectNode.put("direction","desc");
//        }else {
//            objectNode.put("direction","asc");
//        }
//
//        clauseMap.put(ClauseType.SORT,objectNode);
//    }
//
//    private static void extractJsonFromFind(String token,Map<ClauseType,Object> clauseMap ) {
//       String methodParam = StringUtils.substringBetween(StringUtils.substringAfter(token,"find"),"(",")");
//       methodParam = "["+methodParam+"]";
//       JsonNode node = JsonUtil.getJsonNode(methodParam);
//       ClauseType[] clauseTypes = new ClauseType[]{ClauseType.WHERE,ClauseType.SELECT};
//        if(node.isArray()){
//            ArrayNode arrayNode = (ArrayNode) node;
//            for(int i = 0; i < arrayNode.size(); i++) {
//                JsonNode arrayElement = arrayNode.get(i);
//                System.out.println(arrayElement);
//                clauseMap.put(clauseTypes[i],arrayElement);
//            }
//        }
//
//    }
}
