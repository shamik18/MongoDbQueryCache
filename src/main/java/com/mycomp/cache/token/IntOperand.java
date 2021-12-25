package com.mycomp.cache.token;

import java.util.Objects;

public class IntOperand extends Operands{
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean isEquivalent(Token token) {
        if(!(token instanceof IntOperand))
            return false;
        IntOperand intOperand = (IntOperand)token;
        if(intOperand.operatorEnum.equals(this.operatorEnum)){
            if(this.operatorEnum.equals(OperatorEnum.EQ)){
                return intOperand.value.equals(this.value);
            }else if(this.operatorEnum.equals(OperatorEnum.GT)){
                return this.value.intValue() > intOperand.value.intValue();
            }else if(this.operatorEnum.equals(OperatorEnum.LT)){
                return this.value.intValue() < intOperand.value.intValue();
            }
        }
        return false;
    }
}
