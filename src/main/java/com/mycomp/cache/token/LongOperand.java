package com.mycomp.cache.token;

import java.util.Objects;

public class LongOperand extends Operands{
    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public boolean isEquivalent(Token token) {
        if(!(token instanceof LongOperand))
            return false;
        LongOperand longOperand = (LongOperand)token;
        if(longOperand.operatorEnum.equals(this.operatorEnum)){
            if(this.operatorEnum.equals(OperatorEnum.EQ)){
                return longOperand.value.equals(this.value);
            }else if(this.operatorEnum.equals(OperatorEnum.GT)){
                return this.value.longValue() > longOperand.value.longValue();
            }else if(this.operatorEnum.equals(OperatorEnum.LT)){
                return this.value.longValue() < longOperand.value.longValue();
            }
        }
        return false;
    }
}
