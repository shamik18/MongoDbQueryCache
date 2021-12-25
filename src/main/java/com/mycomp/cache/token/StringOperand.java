package com.mycomp.cache.token;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static com.mycomp.cache.token.KeywordLookup.OP_HKEY;

public class StringOperand extends Operands{

    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String query = "";
        if(this.operatorEnum.equals(OperatorEnum.LIKE)){
            query+=OP_HKEY.get(this.operatorEnum)+" "+"'%"+value+"%'";
        }else{
            query+=OP_HKEY.get(OperatorEnum.EQ)+" "+"'"+value+"'";
        }
        return query;
    }

    @Override
    public boolean isEquivalent(Token token) {
        if(!(token instanceof StringOperand))
            return false;
        StringOperand stringOperand = (StringOperand)token;
        if(stringOperand.operatorEnum.equals(OperatorEnum.LIKE) && this.operatorEnum.equals(OperatorEnum.EQ))
            return false;
        String otherVal = null;
        if(stringOperand.operatorEnum.equals(OperatorEnum.LIKE)){
            otherVal = StringUtils.strip(stringOperand.value,"%");
        }
        String thisVal = null;
        if(this.operatorEnum.equals(OperatorEnum.LIKE)){
            thisVal = StringUtils.strip(this.value,"%");
        }
        return Objects.equals(thisVal,otherVal);
    }
}
