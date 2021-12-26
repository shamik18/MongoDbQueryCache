package com.mycomp.cache;

import com.mycomp.cache.clause.Query;
import com.mycomp.cache.builder.QueryBuilder;
import com.mycomp.models.HomeProperty;
import com.mycomp.mongo.gateway.MongoGateway;

import java.util.List;

public class MongoClientWithCache {
    private MongoGateway mongoGateway = new MongoGateway();
    private QueryCache queryCache = new QueryCache();
    private QueryBuilder queryBuilder = new QueryBuilder();


    public CacheResult find(Query query){
        //Find the value in the cache.
        System.out.println("Hsql query:"+query.toString());
        if(!queryCache.isFound(query)){
            System.out.println("Cache miss !!");
            List<HomeProperty> list = mongoGateway.find(query.getMongoQuery(),null);
            //update cache.
           queryCache.updateCache(query,list);
        }else {
            System.out.println("Cache hit !!");
        }
        return queryCache.getQueryFromCache(query);
    }

    public CacheResult excuteQuery(String queryString){
        Query query =queryBuilder.populateQuery(queryString);
        switch (query.getMethodType()){
            case METHOD_FIND:
            case METHOD_AGGREGATE:
                return find(query);
        }
        return null;
    }
}
