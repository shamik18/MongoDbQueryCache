package com.mycomp.cache;

import com.mycomp.cache.token.Query;
import com.mycomp.h2.JPAHandler;
import com.mycomp.models.HomeProperty;

import java.util.*;

public class QueryCache {

    private JPAHandler jpaHandler = new JPAHandler();
    private class PriorityQueueSet extends HashSet<Query>{
        private PriorityQueue<Query> queue = new PriorityQueue<>((u,v) -> u.getUpdateTS().compareTo(v.getUpdateTS()));
        private static final  int CACHE_SIZE = 4;
        @Override
        public boolean add(Query query) {
            boolean find = super.add(query);
            cacheReplacement(query);
            return find;
        }
        private void cacheReplacement(Query query){
            if(queue.size() == CACHE_SIZE){
                queue.remove();
            }
            queue.offer(query);
        }
        public Query get(Query query){
            for ( Iterator<Query> it = this.iterator(); it.hasNext();){
                Query item = it.next();
                if(item.isEquivalent(query)){
                    return query;
                }
            }
            return null;
        }

        public boolean isExist(Query query){
            Query q = get(query);
            return q != null;
        }
    }

    private PriorityQueueSet lruCache = new PriorityQueueSet();

    public boolean isFound(Query query){
       return lruCache.isExist(query);
    }

    public List<HomeProperty> getQueryFromCache(Query query){
        String hsqlQuery = query.toString();
        List<HomeProperty>  homeProperties = jpaHandler.getDataFromCache(hsqlQuery);
        Query q = lruCache.get(query);
        q.setUpdateTS(new Date());
        return homeProperties;
    }

    public void updateCache(Query query, List<HomeProperty> list){
        for(HomeProperty homeProperty: list) {
            jpaHandler.updateCache(homeProperty);
        }
        lruCache.add(query);
    }



}
