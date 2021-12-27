package com.mycomp.cache.clause;

import com.mycomp.cache.enums.MethodType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Query {
    private SelectClause selectClause;
    private FromClause fromClause;
    private WhereClause whereClause;
    private GroupByClause groupByClause;
    private OrderByClause orderByClause;
    private RecLimit recLimit;
    private MethodType methodType;
    private String mongoQuery;
    private LocalDateTime updateTS = LocalDateTime.now();
    private String queryID = UUID.randomUUID().toString();

    public String getQueryID(){
        return queryID;
    }

    public LocalDateTime getUpdateTS() {
        return updateTS;
    }

    public void setUpdateTS(LocalDateTime updateTS) {
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
        if(selectClause != null ){
            query+=selectClause.toString();
        }
        if(fromClause != null ){
            query+=" "+fromClause;
        }
        if(whereClause != null ){
            query+=" "+whereClause;
        }
        if(groupByClause != null ){
            query+=" "+groupByClause;
        }
        if(orderByClause != null){
            query+=" "+orderByClause;
        }
//        System.out.println(query);
        return query;
    }

    public boolean isEquivalent(Query other){
     return this.whereClause.isEquivalent(other.whereClause);
    }
}
