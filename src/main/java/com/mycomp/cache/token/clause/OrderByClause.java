package com.mycomp.cache.token.clause;

import com.mycomp.cache.token.clause.BasicClause;
import org.apache.commons.lang3.StringUtils;

public class OrderByClause extends BasicClause {
    @Override
    public String toString() {
        setApply(false);
        return StringUtils.EMPTY;
    }
}
