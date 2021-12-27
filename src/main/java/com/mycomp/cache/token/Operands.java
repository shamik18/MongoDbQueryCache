package com.mycomp.cache.token;

import com.mycomp.cache.CacheResult;
import com.mycomp.cache.constant.KeywordLookup;
import com.mycomp.cache.enums.OperatorEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Operands implements Token {
    private static final Logger logger = LogManager.getLogger(Operands.class);

    protected OperatorEnum operatorEnum = OperatorEnum.EQ;
    protected String operator = "=";

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
        operatorEnum = KeywordLookup.KEY_OP.get(operator);
    }

    public OperatorEnum operatorEnum() {
        return operatorEnum;
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
