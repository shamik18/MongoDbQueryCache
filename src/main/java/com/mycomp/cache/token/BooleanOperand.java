package com.mycomp.cache.token;

import java.util.Objects;

public class BooleanOperand extends Operands{
   private Boolean value;

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public boolean isEquivalent(Token token) {
        if(!(token instanceof BooleanOperand))
            return false;
        BooleanOperand booleanOperand = (BooleanOperand) token;
        if(booleanOperand.operatorEnum.equals(this.operatorEnum)
                && booleanOperand.value.equals(this.value)){
            return true;
        }
        return false;
    }
}
