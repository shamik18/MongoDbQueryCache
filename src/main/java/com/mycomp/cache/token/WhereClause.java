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
           if(other.constrain.getConstrain_name().equals("and")){
               for(Attribute attribute: other.constrain.getAttributes()){
                   if(attribute.isEquvalent(other.getAttribute())){
                       return true;
                   }
               }
           }
        }
        if(other.attrbType && !this.attrbType){
            if(this.constrain.getConstrain_name().equals("or")){
                for(Attribute attribute : this.constrain.getAttributes()){
                    if(attribute.isEquvalent(other.attribute)){
                        return true;
                    }
                }
            }

        }
        if(!other.attrbType && !this.attrbType){
            if(other.constrain.getConstrain_name().equals("or")){
                if(this.constrain.getConstrain_name().equals("or")){
                    if(!this.attribute.isEquvalent(other.attribute)){
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
