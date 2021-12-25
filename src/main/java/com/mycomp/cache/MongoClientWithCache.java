package com.mycomp.cache;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mycomp.cache.token.Query;
import com.mycomp.cache.token.QueryBuilder;
import com.mycomp.h2.JPAHandler;
import com.mycomp.models.HomeProperty;
import com.mycomp.mongo.gateway.MongoGateway;
import com.mycomp.util.JsonUtil;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MongoClientWithCache {
    private MongoGateway mongoGateway = new MongoGateway();
    private QueryCache queryCache = new QueryCache();
    private QueryBuilder queryBuilder = new QueryBuilder();


    public List<HomeProperty> find(Query query){
        //Find the value in the cache.
        if(!queryCache.isFound(query)){
            System.out.println("Cache miss !!");
            List<HomeProperty> list = mongoGateway.find(query.getMongoQuery(),null);
            //update cache.
           queryCache.updateCache(query,list);
        }
        return queryCache.getQueryFromCache(query);
    }

    public List<HomeProperty> excuteQuery(String queryString){
        Query query =queryBuilder.populateQuery(queryString);
        switch (query.getMethodType()){
            case METHOD_FIND:
            case METHOD_AGGREGATE:
                return find(query);
        }
        return null;
    }
}
