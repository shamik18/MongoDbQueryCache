package com.mycomp.cache.token;

import java.util.Set;

public class Constrain implements Token{
    private String attribute_name;
    private Set<Constrain> childConstains;
    private Set<Operands> operands;

    public String getAttribute_name() {
        return attribute_name;
    }

    public void setAttribute_name(String attribute_name) {
        this.attribute_name = attribute_name;
    }

    public Set<Constrain> getChildConstains() {
        return childConstains;
    }

    public void setChildConstains(Set<Constrain> childConstains) {
        this.childConstains = childConstains;
    }

    public Set<Operands> getOperands() {
        return operands;
    }

    public void setOperands(Set<Operands> operands) {
        this.operands = operands;
    }
}
