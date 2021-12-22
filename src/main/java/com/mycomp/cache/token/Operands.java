package com.mycomp.cache.token;

public class Operands implements Token {
    protected OperatorEnum operatorEnum;

    public OperatorEnum getOperatorEnum() {
        return operatorEnum;
    }

    public void setOperatorEnum(OperatorEnum operatorEnum) {
        this.operatorEnum = operatorEnum;
    }
}
