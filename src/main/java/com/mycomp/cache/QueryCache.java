package com.mycomp.cache;

import com.mycomp.h2.JPAHandler;
import com.mycomp.models.HomeProperty;

import java.util.ArrayList;
import java.util.List;

public class QueryCache {
    private JPAHandler jpaHandler = new JPAHandler();

    public boolean isFound(String query){
        return false;
    }

    public List<HomeProperty> getQueryFromCache(String query){
        String hsql = "from HomeProperty hp";
        String whereHsql=" where hp.name like :name";
        whereHsql+=" or";
        whereHsql+=" hp.propertyType= :type";
        hsql+=whereHsql;
        List<Object> parameter = new ArrayList<>();
        parameter.add("Beach");
        parameter.add("House");
        return jpaHandler.getDataFromCache(hsql,parameter);
    }

    public void updateCache(Object object){
        //Find the value in the cache.
        List<HomeProperty> list = (List<HomeProperty>) object;
        //update cache.
        for(HomeProperty homeProperty: list) {
            jpaHandler.updateCache(homeProperty);
        }
    }
}
