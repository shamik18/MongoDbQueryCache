package com.mycomp.cache;

import com.mycomp.cache.clause.Query;
import com.mycomp.cache.builder.QueryBuilder;
import com.mycomp.models.HomeProperty;
import com.mycomp.mongo.gateway.MongoGateway;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.codecs.jsr310.LocalDateCodec;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public class MongoClientWithCache {
    private static final Logger logger = LogManager.getLogger(MongoClientWithCache.class);

    private MongoGateway mongoGateway ;
    private QueryCache queryCache = new QueryCache();
    private QueryBuilder queryBuilder = new QueryBuilder();

    public MongoClientWithCache(String url){
        this.mongoGateway = new MongoGateway(url);
    }


    public CacheResult find(Query query){
        //Find the value in the cache.
        logger.info("Hsql query with id {} :{}",query.getQueryID(),query.toString());
        LocalDateTime startTime  = LocalDateTime.now();
        logger.info("Time to look for the query in cache {}",startTime);
        if(!queryCache.isFound(query)){
            logger.info("Queru id {} Cache miss !!",query.getQueryID());
            List<HomeProperty> list = mongoGateway.find(query.getMongoQuery(),null);
            //update cache.
           queryCache.updateCache(query,list);
        }else {
            logger.info("Query id {} Cache hit !!",query.getQueryID());
        }
        LocalDateTime endTime  = LocalDateTime.now();
        logger.info("Time to look for the query in cache {}",startTime);
//        long duration = Duration.between(endTime.toLocalDate(),startTime.toLocalDate()).toMillis();
//        System.out.println(duration);
//        logger.info("Response Time:"+ duration);
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
