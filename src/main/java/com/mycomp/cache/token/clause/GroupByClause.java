package com.mycomp.cache.token.clause;

import org.apache.commons.lang3.StringUtils;

public class GroupByClause extends BasicClause {
    @Override
    public String toString() {
        setApply(false);
        return StringUtils.EMPTY;
    }
}
