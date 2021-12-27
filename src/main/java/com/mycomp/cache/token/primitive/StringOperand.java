package com.mycomp.cache.token.primitive;

import com.mycomp.cache.enums.OperatorEnum;
import com.mycomp.cache.token.Operands;
import com.mycomp.cache.token.Token;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static com.mycomp.cache.constant.KeywordLookup.OP_HKEY;

public class StringOperand extends Operands {
    private static final Logger logger = LogManager.getLogger(LongOperand.class);

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
