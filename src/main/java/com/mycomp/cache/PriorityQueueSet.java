package com.mycomp.cache;

import com.mycomp.app.AppCmd;
import com.mycomp.cache.clause.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;

public class PriorityQueueSet {
    private static final Logger logger = LogManager.getLogger(PriorityQueueSet.class);

    private PriorityQueue<Query> queue = new PriorityQueue<>((u, v) -> u.getUpdateTS().compareTo(v.getUpdateTS()));
    private LinkedHashSet<Query> set = new LinkedHashSet<>();
    private static final  int CACHE_SIZE = 3;

    public boolean add(Query query) {
        boolean find = set.add(query);
        cacheReplacement(query);
        return find;
    }
    private void cacheReplacement(Query query){
        if(queue.size() == CACHE_SIZE){
            logger.info("Cache if full");
            Query q = queue.remove();
            logger.info("The query with id {} replace as it is Least Recently Used [{}]",q.getQueryID(),q.getUpdateTS());
        }
        queue.offer(query);
    }
    public Query get(Query query){
        for (Iterator<Query> it = set.iterator(); it.hasNext();){
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
