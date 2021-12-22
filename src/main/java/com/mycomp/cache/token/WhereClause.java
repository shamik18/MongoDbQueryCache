package com.mycomp.cache.token;

public class WhereClause {
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
        if(other.attrbType && this.attrbType){
            return  attribute.isEquvalent(other.attribute);
        }
        if(other.attrbType!=true && this.attrbType){
            //some logic
        }
        return false;
    }
}
