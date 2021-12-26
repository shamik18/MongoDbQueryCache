package com.mycomp.cache.clause;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SelectClause extends BasicClause {
    private List<String> fields = new ArrayList<>();
    private String aggregateFunction ;
    private boolean aggregate;

    public boolean isAggregate() {
        return aggregate;
    }

    public void setAggregate(boolean aggregate) {
        this.aggregate = aggregate;
    }

    public String getAggregateFunction() {
        return aggregateFunction;
    }

    public void setAggregateFunction(String aggregateFunction) {
        this.aggregateFunction = aggregateFunction;
        if(this.aggregateFunction != null){
            this.setAggregate(true);
        }
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        String select = "select ";
        int len = fields.size();
        if(aggregateFunction != null){
            select+=aggregateFunction+" ";
            setApply(true);
        }else {
            if(fields.size() == 0){
                select+= StringUtils.EMPTY;
                setApply(false);
            }else{
                setApply(true);
                for(int i =0 ; i<len; i++){
                    select+=fields.get(i);
                    if(i != (len-1))
                        select+=",";
                }
            }
        }

        return select;
    }
}
