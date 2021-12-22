package com.mycomp.cache.token;

import java.util.Objects;

public class Attribute {
    private String attr_name;
    private String operation;
    private String type;
    private Object value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return Objects.equals(attr_name, attribute.attr_name) && Objects.equals(operation, attribute.operation) && Objects.equals(type, attribute.type) && Objects.equals(value, attribute.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attr_name, operation, type, value);
    }

    public boolean isEquvalent(Attribute attribute) {
        if(attribute.attr_name.equals(this.attr_name)){

        }
        return false;
    }
}
