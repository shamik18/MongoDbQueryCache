package com.mycomp.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class JsonUtil {
    private static Logger logger = LogManager.getLogger(JsonUtil.class);
    private static final ObjectMapper OBJECT_MAPPER ;
    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        OBJECT_MAPPER.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,true);
    }
    public static String toJsonString(Object pojo){
        String json = null;
        try{
            json = OBJECT_MAPPER.writeValueAsString(pojo);
        }catch(JsonProcessingException e){
            logger.fatal("Exception Coverting Json",e);
            throw new IllegalArgumentException("The given Json object value: "+pojo+" cannot be transformed to a String: "+e.getMessage());
        }
        return json;
    }

   public static <T> T toPojo(String jsonStr, Class<T> tClass){
        try{
            return OBJECT_MAPPER.readValue(jsonStr,tClass);
        } catch (JsonMappingException e) {
            logger.fatal("Exception Coverting Pojo",e);
            throw new IllegalArgumentException("The given Json string value: "+jsonStr+" cannot be transformed to a Pojo: "+e.getMessage());
        } catch (JsonProcessingException e) {
            logger.fatal("Exception Coverting Pojo",e);
            throw new IllegalArgumentException("The given Json string value: "+jsonStr+" cannot be transformed to a Pojo: "+e.getMessage());
        }
   }

   public static JsonParser getJsonParser(String stringJson){
       try {
           return OBJECT_MAPPER.getFactory().createParser(stringJson);
       } catch (IOException e) {
           e.printStackTrace();
           logger.fatal("Exception Coverting Pojo",e);
           throw new IllegalArgumentException("The given Json string value: "+stringJson+" cannot be transformed to a Pojo: "+e.getMessage());
       }
   }
}
