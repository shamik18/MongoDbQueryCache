package com.mycomp.cache.token;

import java.util.Set;

public class Constrain implements Token{
    private String constrain_name;
    private Set<Constrain> childConstains;
    private Set<Attribute> attributes;

    public String getConstrain_name() {
        return constrain_name;
    }

    public void setConstrain_name(String constrain_name) {
        this.constrain_name = constrain_name;
    }

    public Set<Constrain> getChildConstains() {
        return childConstains;
    }

    public void setChildConstains(Set<Constrain> childConstains) {
        this.childConstains = childConstains;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }
}
