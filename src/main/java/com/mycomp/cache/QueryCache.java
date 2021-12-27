package com.mycomp.cache;

import com.mycomp.cache.clause.Query;
import com.mycomp.cache.clause.RecLimit;
import com.mycomp.h2.JPAHandler;
import com.mycomp.models.HomeProperty;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class QueryCache {
    private static final Logger logger = LogManager.getLogger(QueryCache.class);

    private JPAHandler jpaHandler = new JPAHandler();
    private PriorityQueueSet lruCache = new PriorityQueueSet();

    public boolean isFound(Query query){
       return lruCache.isExist(query);
    }

    public CacheResult getQueryFromCache(Query query){
        String hsqlQuery = query.toString();
        RecLimit recLimit = query.getRecLimit();
        boolean isAggrigate = query.getSelectClause().isAggregate();
        logger.info("fetching from the cache with query id {}",query.getQueryID());
        CacheResult result = jpaHandler.getDataFromCache(hsqlQuery,recLimit,isAggrigate);
        Query q = lruCache.get(query);
        q.setUpdateTS(LocalDateTime.now() );
        logger.info("updating time stamp for query id {} with {}",q.getQueryID(),q.getUpdateTS());
        return result;
    }

    public void updateCache(Query query, List<HomeProperty> list){
        for(HomeProperty homeProperty: list) {
            jpaHandler.updateCache(homeProperty);
        }
        lruCache.add(query);
    }
}
