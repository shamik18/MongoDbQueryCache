package com.mycomp.cache.token;

import com.mycomp.cache.token.clause.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;

public class Query {
    private SelectClause selectClause;
    private FromClause fromClause;
    private WhereClause whereClause;
    private GroupByClause groupByClause;
    private OrderByClause orderByClause;
    private RecLimit recLimit;
    private MethodType methodType;
    private String mongoQuery;
    private Date updateTS = new Date();

    public Date getUpdateTS() {
        return updateTS;
    }

    public void setUpdateTS(Date updateTS) {
        this.updateTS = updateTS;
    }

    public MethodType getMethodType() {
        return methodType;
    }

    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
    }

    public String getMongoQuery() {
        return mongoQuery;
    }

    public void setMongoQuery(String mongoQuery) {
        this.mongoQuery = mongoQuery;
    }

    public SelectClause getSelectClause() {
        return selectClause;
    }

    public void setSelectClause(SelectClause selectClause) {
        this.selectClause = selectClause;
    }

    public FromClause getFromClause() {
        return fromClause;
    }

    public void setFromClause(FromClause fromClause) {
        this.fromClause = fromClause;
    }

    public WhereClause getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(WhereClause whereClause) {
        this.whereClause = whereClause;
    }

    public GroupByClause getGroupByClause() {
        return groupByClause;
    }

    public void setGroupByClause(GroupByClause groupByClause) {
        this.groupByClause = groupByClause;
    }

    public OrderByClause getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(OrderByClause orderByClause) {
        this.orderByClause = orderByClause;
    }

    public RecLimit getRecLimit() {
        return recLimit;
    }

    public void setRecLimit(RecLimit recLimit) {
        this.recLimit = recLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Query)) return false;
        Query query = (Query) o;
        return this.isEquivalent(query);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWhereClause());
    }

    @Override
    public String toString() {
        String query = "";
        if(selectClause != null &&  selectClause.isApply()){
            query+=selectClause.toString();
        }
        if(fromClause != null && fromClause.isApply()){
            query+=" "+fromClause;
        }
        if(whereClause != null && whereClause.isApply()){
            query+=" "+whereClause;
        }
        if(groupByClause != null && groupByClause.isApply()){
            query+=" "+groupByClause;
        }
        if(orderByClause != null && orderByClause.isApply()){
            query+=" "+orderByClause;
        }
        System.out.println(query);
        return query;
    }

    public boolean isEquivalent(Query other){
     return this.whereClause.isEquivalent(other.whereClause);
    }
}
