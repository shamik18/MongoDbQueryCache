package com.mycomp.cache.token;

import java.util.HashMap;
import java.util.Map;

public class MongoToHsqlKeyMapper {
    private static final Map<String,String> KEY_MAPPER = new HashMap<>();
    static {
        KEY_MAPPER.put("$or","or");
        KEY_MAPPER.put("$and","and");
        KEY_MAPPER.put("or","or");
        KEY_MAPPER.put("and","and");
        KEY_MAPPER.put("$regex","like");
        KEY_MAPPER.put("$gt",">");
        KEY_MAPPER.put("$lt","<");
        KEY_MAPPER.put("=","=");
    }
}
