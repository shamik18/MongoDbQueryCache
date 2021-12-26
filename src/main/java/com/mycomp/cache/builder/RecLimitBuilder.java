package com.mycomp.cache.builder;

import com.mchange.lang.IntegerUtils;
import com.mycomp.cache.clause.Query;
import com.mycomp.cache.clause.RecLimit;

public class RecLimitBuilder {

    public RecLimit populateRecLimit(Query query, Object o) {
        RecLimit recLimit = query.getRecLimit();
        if(recLimit == null){
            recLimit = new RecLimit();
        }
        if(o instanceof String){
            String str = (String) o;
            int maxLimit = IntegerUtils.parseInt(str,0);
            if(maxLimit > 0){
                recLimit.setLimitApply(true);
                recLimit.setLowLimit(0);
                recLimit.setHighLimit(maxLimit);
            }
        }
        return recLimit;
    }


}
