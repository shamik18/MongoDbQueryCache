package com.mycomp.h2;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycomp.util.JsonUtil;

import java.io.IOException;

public class JsonToHsqlConvertor {

    public String jsontoHSQLConvertor(){
        return null;
    }

    public static void main(String[] args) {
        String str1 = "{ \"$or\" : [ {\"name\":{\"$regex\":\"Beach\"} } , { \"property_type\":\"House\"} ] }";
        JsonParser jsonParser = JsonUtil.getJsonParser(str1);
        while (!jsonParser.isClosed()){
            try {
                JsonToken jsonToken = jsonParser.nextToken();
                System.out.println(jsonToken);
                if(JsonToken.FIELD_NAME.equals(jsonToken)){
                    String fieldName = jsonParser.currentName();
                    System.out.println(fieldName);
                }
                if(JsonToken.VALUE_STRING.equals(jsonToken)){
                    System.out.println(jsonParser.getValueAsString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
