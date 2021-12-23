package com.mycomp.cache.token;

public class Operands implements Token {
    protected OperatorEnum operatorEnum = OperatorEnum.EQ;
    protected String operator = "=";

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public OperatorEnum getOperatorEnum() {
        return operatorEnum;
    }

    public void setOperatorEnum(OperatorEnum operatorEnum) {
        this.operatorEnum = operatorEnum;
    }
}
