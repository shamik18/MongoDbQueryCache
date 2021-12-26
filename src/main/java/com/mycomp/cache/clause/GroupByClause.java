package com.mycomp.cache.clause;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Set;

public class GroupByClause extends BasicClause {
    private Set<String> groupByField;
    private Map<String,String> map;
    private Map<String, Object> having;
    private boolean groupByInd;

    public boolean groupByInd() {
        return groupByInd;
    }

    public void setGroupByInd(boolean groupByInd) {
        this.groupByInd = groupByInd;
    }

    public Set<String> groupByField() {
        return groupByField;
    }

    public void setGroupByField(Set<String> groupByField) {
        this.groupByField = groupByField;
        setApply(true);
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setHaving(Map<String, Object> having) {
        this.having = having;
    }

    @Override
    public String toString() {
       String q = StringUtils.EMPTY;
      if(isApply()){
          q+="group by ";
          for(String fieldName: groupByField){
              q+=fieldName+" ";
          }
      }
        return q;
    }
}
