package com.mycomp.cache.token.clause;

import com.mycomp.cache.token.clause.BasicClause;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SelectClause extends BasicClause {
    private List<String> fields = new ArrayList<>();

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
        if(fields.size() == 0){
            select+= StringUtils.EMPTY;
            setApply(false);
        }else {
           for(int i =0 ; i<len; i++){
               select+=fields.get(i);
               if(i != (len-1))
                   select+=",";
           }
        }
        return select;
    }
}
