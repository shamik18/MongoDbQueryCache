package com.mycomp.cache.clause;

import com.mycomp.cache.token.Attribute;
import com.mycomp.cache.token.Constrain;
import org.apache.commons.lang3.StringUtils;

public class WhereClause extends BasicClause {
    private Constrain constrain;
    private Attribute attribute;
    private boolean attrbType = false;

    public Constrain getConstrain() {
        return constrain;
    }

    public void setConstrain(Constrain constrain) {
        this.constrain = constrain;
        this.attrbType = false;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
        this.attrbType = true;
    }

    public boolean isAttrbType() {
        return attrbType;
    }

    public void setAttrbType(boolean attrbType) {
        this.attrbType = attrbType;
    }

    public boolean isEquivalent(WhereClause other){
        if(this.isAttrbType() && other.isAttrbType()){
            return attribute.isEquivalent(other.getAttribute());
        }else if (this.isAttrbType() && !other.isAttrbType()){
            return attribute.isEquivalent(other.getConstrain());
        }else if(!this.isAttrbType() && other.isAttrbType()){
            return constrain.isEquivalent(other.getAttribute());
        }else if(!this.isAttrbType() && !other.isAttrbType()){
            return constrain.isEquivalent(other.getConstrain());
        }
        return false;
    }

    @Override
    public String toString() {
        String query = StringUtils.EMPTY;
        String where = "where ";
        if(attrbType){
            query+=attribute.toString();
        }else{
           query+= constrain.toString();
        }
        if(StringUtils.isNotBlank(query)){
            query=where+query;
        }else{
            setApply(false);
        }
        return query;
    }
}
