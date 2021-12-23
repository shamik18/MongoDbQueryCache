package com.mycomp.cache.token;

import java.util.ArrayList;
import java.util.List;

public class SelectClause {
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
            select+="*";
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
