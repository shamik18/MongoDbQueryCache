package com.mycomp.cache.token.clause;

public class FromClause extends BasicClause{
    String from;

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return from;
    }
}
