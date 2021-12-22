package com.mycomp.cache.token;

import java.util.Objects;

public class Query {
    private SelectClause selectClause;
    private FromClause fromClause;
    private WhereClause whereClause;
    private GroupByClause groupByClause;
    private OrderByClause orderByClause;
    private RecLimit recLimit;

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
        return Objects.equals(getWhereClause(), query.getWhereClause());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWhereClause());
    }
}
