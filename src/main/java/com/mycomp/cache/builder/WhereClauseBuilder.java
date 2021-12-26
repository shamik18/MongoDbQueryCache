package com.mycomp.cache.builder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.mycomp.cache.clause.WhereClause;
import com.mycomp.cache.token.Attribute;
import com.mycomp.cache.token.Constrain;
import com.mycomp.cache.token.Operands;
import com.mycomp.cache.token.Token;
import com.mycomp.cache.token.primitive.BooleanOperand;
import com.mycomp.cache.token.primitive.IntOperand;
import com.mycomp.cache.token.primitive.StringOperand;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

import static com.mycomp.cache.constant.KeywordLookup.ComjuctureSet;
import static com.mycomp.cache.constant.KeywordLookup.OperatorSet;

public class WhereClauseBuilder {
    public WhereClause populateWhereClause(Object jsonWhere) {
        Stack<Object> stack = new Stack<>();
        if(!(jsonWhere instanceof JsonNode)){
            throw new RuntimeException("Not a valid where clause json node");
        }
        JsonNode node = (JsonNode) jsonWhere;
        JsonParser jsonParser = node.traverse();
        while (!jsonParser.isClosed()){
            try {
                JsonToken jsonToken = jsonParser.nextToken();
                if(JsonToken.START_OBJECT.equals(jsonToken)){
                    stack.push(JsonToken.START_OBJECT);
                }else if(JsonToken.FIELD_NAME.equals(jsonToken)){
                    String name = jsonParser.currentName();
                    if(ComjuctureSet.contains(name)){
                        Constrain constrain = new Constrain();
                        constrain.setConstrain_name(name);
                        stack.push(constrain);
                    }else if(OperatorSet.contains(name)){
                        Operands operands = new Operands();
                        operands.setOperator(name);
                        stack.push(operands);
                    }else {
                        Attribute attribute = new Attribute();
                        attribute.setAttr_name(name);
                        stack.push(attribute);
                    }
                }else if(JsonToken.VALUE_STRING.equals(jsonToken)){
                    String s = jsonParser.getValueAsString();
                    stack.push(s);
                }else if(JsonToken.VALUE_NUMBER_INT.equals(jsonToken)){
                    Integer integer = jsonParser.getValueAsInt();
                    stack.push(integer);
                }else if(JsonToken.VALUE_NUMBER_FLOAT.equals(jsonToken)){
                    Double db = jsonParser.getValueAsDouble();
                    stack.push(db);
                }else if(JsonToken.VALUE_TRUE.equals(jsonToken) || JsonToken.VALUE_FALSE.equals(jsonToken)){
                    Boolean boolVal = jsonParser.getValueAsBoolean();
                    stack.push(boolVal);
                }else if(JsonToken.END_OBJECT.equals(jsonToken)){
                    Object previousValue = null;
                    Set<Token> attrubuteSet = new HashSet<>();
                    Set<Operands> operandSet = new HashSet<>();

                    while (true){
                        Object elem = stack.pop();
                        if(elem instanceof  JsonToken ){
                            JsonToken tokenElem = (JsonToken) elem;
                            if(tokenElem.equals(JsonToken.START_OBJECT)){
                                if(previousValue instanceof  Operands){
                                    Attribute attr = null;
                                    if(stack.peek() instanceof  Attribute)
                                        attr = (Attribute) stack.pop();
                                    Attribute a = createAttribute(attr,(Operands) previousValue);
                                    stack.push(a);
                                    if(!operandSet.isEmpty()){
                                        for (Operands op: operandSet){
                                            a = createAttribute(attr,op);
                                            stack.push(a);
                                        }
                                    }
                                }else{
                                    if(!attrubuteSet.isEmpty()){
                                        attrubuteSet.add((Token)previousValue);
                                        Constrain constrain = new Constrain();
                                        constrain.setConstrain_name("$and");
                                        constrain.setChild(attrubuteSet);
                                        previousValue = constrain;
                                    }

                                    stack.push(previousValue);
                                }

                                break;
                            }

                        }else if(elem instanceof Attribute){
                            Attribute attribute = (Attribute) elem;
                            if(previousValue != null && previousValue instanceof  String){
                                StringOperand stringOperand = new StringOperand();
                                stringOperand.setValue((String) previousValue);
                                Set<Operands> ops = new HashSet<>();
                                ops.add(stringOperand);
                                attribute.setOperandsSet(ops);
                            }else if(previousValue != null && previousValue instanceof  Integer){
                                IntOperand intOperand = new IntOperand();
                                intOperand.setValue((Integer) previousValue);
                                Set<Operands> ops = new HashSet<>();
                                ops.add(intOperand);
                                attribute.setOperandsSet(ops);
                            }else if(previousValue != null && previousValue instanceof  Boolean) {
                                BooleanOperand booleanOperand = new BooleanOperand();
                                booleanOperand.setValue((Boolean) previousValue);
                                Set<Operands> ops = new HashSet<>();
                                ops.add(booleanOperand);
                                attribute.setOperandsSet(ops);
                            }else if(previousValue != null && previousValue instanceof  Operands) {
                                Operands operands = (Operands) previousValue;
                                Set<Operands> ops = new HashSet<>();
                                ops.add(operands);
                                attribute.setOperandsSet(ops);
                            }else if(previousValue != null && previousValue instanceof  Attribute) {
                                attrubuteSet.add((Token)previousValue);
                            }else if(previousValue != null && previousValue instanceof  Constrain) {
                                attrubuteSet.add((Token)previousValue);
                            }else if(previousValue != null && previousValue instanceof Set){
                                attribute.setOperandsSet((Set<Operands>) previousValue);
                            }
                            previousValue = attribute;
                        }else if(elem instanceof Operands){
                            Operands operands = (Operands) elem;
                            if(previousValue != null && previousValue instanceof  String){
                                StringOperand stringOperand = new StringOperand();
                                stringOperand.setOperator(operands.getOperator());
                                stringOperand.setValue((String) previousValue);
                                operands = stringOperand;
                            }else if(previousValue != null && previousValue instanceof  Integer){
                                IntOperand intOperand = new IntOperand();
                                intOperand.setOperator(operands.getOperator());
                                intOperand.setValue((Integer) previousValue);
                                operands = intOperand;
                            }else if(previousValue != null && previousValue instanceof  Boolean) {
                                BooleanOperand booleanOperand = new BooleanOperand();
                                booleanOperand.setOperator(operands.getOperator());
                                booleanOperand.setValue((Boolean) previousValue);
                                operands = booleanOperand;
                            }else if(previousValue != null && previousValue instanceof  Operands){
                                operandSet.add((Operands) previousValue);
                            }else if(previousValue != null && previousValue instanceof  Attribute){
                                attrubuteSet.add((Attribute)previousValue);
                            }
                            previousValue = operands;
                        }else if(elem == null || elem instanceof String || elem instanceof Integer || elem instanceof Boolean ){
                            if(elem == null){
                                previousValue = Optional.empty();
                            }else {
                                if(previousValue == null)
                                    previousValue = elem;
                                else if(previousValue instanceof Operands){
                                    operandSet.add((Operands) previousValue);
                                    previousValue = elem;
                                }
                            }
                        }else if(elem instanceof Set){
                            previousValue = elem;
                        }else if(elem instanceof Constrain) {
                            Constrain constrain = (Constrain) elem;
                            if(previousValue instanceof  Set){
                                Set set = (Set) previousValue;
                                constrain.setChild(set);
                            }else if(previousValue instanceof Constrain){
                                Set<Token> tokens = new HashSet<>();
                                tokens.add((Constrain)previousValue);
                            }
                            previousValue = constrain;
                        }
                    }
                }else if(JsonToken.START_ARRAY.equals(jsonToken)){
                    stack.push(jsonToken);
                }else if(JsonToken.END_ARRAY.equals(jsonToken)){
                    Set<Token> tokens = new HashSet<>();
                    while (true){
                        Object elem = stack.pop();
                        if(elem instanceof  JsonToken ){
                            JsonToken tokenElem = (JsonToken) elem;
                            if(tokenElem.equals(JsonToken.START_ARRAY)){
                                if(stack.peek() instanceof  Constrain){
                                    Constrain constrain = (Constrain) stack.pop();
                                    constrain.setChild(tokens);
                                    stack.push(constrain);
                                }else{
                                    stack.push(tokens);
                                }
                                break;
                            }
                        }
                        if(elem instanceof Token){
                            tokens.add((Token) elem);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        WhereClause whereClause = new WhereClause();
        if(stack.size() == 1){
            Object ob = stack.pop();
            if(ob instanceof Constrain){
                whereClause.setConstrain((Constrain) ob);
            }else if(ob instanceof  Attribute){
                whereClause.setAttribute((Attribute) ob);
            }
        }
//        System.out.println(whereClause);
        return whereClause;
    }

    private Attribute createAttribute(Attribute attribute, Operands operands){
        Attribute a = new Attribute();
        a.setAttr_name(attribute.getAttr_name());
        Set<Operands> operandsSet = new HashSet<>();
        operandsSet.add(operands);
        a.setOperandsSet(operandsSet);
        return a;
    }

}
