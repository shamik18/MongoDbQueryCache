package com.mycomp.cache;

import com.mycomp.h2.JPAHandler;
import com.mycomp.models.HomeProperty;
import com.mycomp.mongo.gateway.MongoGateway;

import java.util.List;

public class MongoClientWithCache {
    private MongoGateway mongoGateway = new MongoGateway();
    private QueryCache queryCache = new QueryCache();


    public List<HomeProperty> find(String query){
        //Find the value in the cache.
        if(!queryCache.isFound(query)){
            System.out.println("Cache miss !!");
            List<HomeProperty> list = mongoGateway.find(query,null);
            //update cache.
           queryCache.updateCache(list);
        }
        return queryCache.getQueryFromCache(query);
    }
}
