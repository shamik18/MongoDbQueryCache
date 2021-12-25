package com.mycomp.cache.token;

import java.util.Objects;

public class Operands implements Token {
    protected OperatorEnum operatorEnum = OperatorEnum.EQ;
    protected String operator = "=";

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
        operatorEnum = KeywordLookup.KEY_OP.get(operator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operands)) return false;
        Operands operands = (Operands) o;
        return operatorEnum == operands.operatorEnum && Objects.equals(getOperator(), operands.getOperator());
    }

    @Override
    public int hashCode() {
        return Objects.hash(operatorEnum, getOperator());
    }

    @Override
    public boolean isEquivalent(Token token) {
        return equals(token);
    }
}
