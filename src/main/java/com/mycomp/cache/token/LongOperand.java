package com.mycomp.cache.token;

import java.util.Objects;

public class LongOperand extends Operands{
    Attribute_Type attribute_type = Attribute_Type.INT32;
    Long value;

    public Attribute_Type getAttribute_type() {
        return attribute_type;
    }

    public void setAttribute_type(Attribute_Type attribute_type) {
        this.attribute_type = attribute_type;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LongOperand)) return false;
        LongOperand that = (LongOperand) o;
        return attribute_type == that.attribute_type && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute_type, value);
    }
}
