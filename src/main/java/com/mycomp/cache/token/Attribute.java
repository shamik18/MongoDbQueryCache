package com.mycomp.cache.token;

import java.util.Objects;
import java.util.Set;

public class Attribute {
    private String attr_name;
    private Set<Operands> operandsSet;

    public String getAttr_name() {
        return attr_name;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    public Set<Operands> getOperandsSet() {
        return operandsSet;
    }

    public void setOperandsSet(Set<Operands> operandsSet) {
        this.operandsSet = operandsSet;
    }

    public boolean isEquvalent(Attribute attribute) {
        if(attribute.attr_name.equals(this.attr_name)){

        }
        return false;
    }
}
