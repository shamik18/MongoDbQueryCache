package com.mycomp.cache.clause;

import org.apache.commons.lang3.StringUtils;

public class OrderByClause extends BasicClause {
    private String name;
    private int direction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setApply(true);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        String query = StringUtils.EMPTY;
        if(isApply()){
            query+="order by "+name+" ";
            if(direction == 1){
                query+="desc ";
            }
        }
        return query;
    }
}
