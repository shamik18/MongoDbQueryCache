package com.mycomp.cache.token;

import java.util.Objects;

public class IntOperand extends Operands{
    Attribute_Type attribute_type = Attribute_Type.INT32;
    Integer value;

    public Attribute_Type getAttribute_type() {
        return attribute_type;
    }

    public void setAttribute_type(Attribute_Type attribute_type) {
        this.attribute_type = attribute_type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntOperand)) return false;
        IntOperand that = (IntOperand) o;
        return attribute_type == that.attribute_type && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute_type, value);
    }
}
