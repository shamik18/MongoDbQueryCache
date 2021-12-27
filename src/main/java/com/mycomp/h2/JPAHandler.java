package com.mycomp.h2;

import com.mycomp.app.AppCmd;
import com.mycomp.cache.CacheResult;
import com.mycomp.cache.clause.RecLimit;
import com.mycomp.models.HomeProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class JPAHandler {
    private static final Logger logger = LogManager.getLogger(JPAHandler.class);

    public void updateCache(HomeProperty homeProperty){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
//            System.out.println("Saving "+homeProperty.getId());
            session.saveOrUpdate(homeProperty);
//            System.out.println("Saved successfully  "+homeProperty.getId());
            transaction.commit();
        }catch (Exception exception) {
            exception.printStackTrace();
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    public CacheResult getDataFromCache(String hsqlQuery, RecLimit recLimit, boolean isAggrigate){
        CacheResult cacheResult = new CacheResult();
        Transaction transaction = null;
//        List<HomeProperty> list = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            logger.info("Execute Hsql:"+hsqlQuery);
            Query query = session.createQuery(hsqlQuery);
            if(isAggrigate){
                Object object = query.getSingleResult();
                cacheResult.setValue(object);
                cacheResult.setSingleResult(isAggrigate);
            }else{
                if(recLimit.isLimitApply()){
                    query.setFirstResult(recLimit.getLowLimit());
                    query.setMaxResults(recLimit.getHighLimit());
                }
                List list = query.getResultList();
                cacheResult.setHomeProperties(list);
                logger.info("No of records returned from cache:"+list.size());
            }
            transaction.commit();
        }catch (Exception exception) {
            exception.printStackTrace();
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return cacheResult;
    }
}
