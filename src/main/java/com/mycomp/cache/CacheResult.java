package com.mycomp.cache;

import com.mycomp.models.HomeProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CacheResult {
    private static final Logger logger = LogManager.getLogger(CacheResult.class);
    private List homeProperties;
    private Object value;
    private boolean singleResult;

    public boolean isSingleResult() {
        return singleResult;
    }

    public void setSingleResult(boolean singleResult) {
        this.singleResult = singleResult;
    }

    public List getHomeProperties() {
        return homeProperties;
    }

    public void setHomeProperties(List homeProperties) {
        this.homeProperties = homeProperties;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
