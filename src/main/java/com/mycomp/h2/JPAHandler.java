package com.mycomp.h2;

import com.mycomp.models.HomeProperty;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class JPAHandler {
    public void updateCache(HomeProperty homeProperty){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            System.out.println("Saving "+homeProperty.getId());
            session.saveOrUpdate(homeProperty);
            System.out.println("Saved successfully  "+homeProperty.getId());
            transaction.commit();
        }catch (Exception exception) {
            exception.printStackTrace();
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<HomeProperty> getDataFromCache(String hsqlQuery){
        Transaction transaction = null;
        List<HomeProperty> list = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            System.out.println("Execute Hsql:"+hsqlQuery);
            Query query = session.createQuery(hsqlQuery);

//            query.setParameter("name",parameter.get(0));
//            query.setParameter("type",parameter.get(1));


            list = query.getResultList();
            System.out.println(list.size());
            transaction.commit();
        }catch (Exception exception) {
            exception.printStackTrace();
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return list;
    }
}
