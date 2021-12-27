package com.mycomp.cache.token.primitive;

import com.mycomp.cache.enums.OperatorEnum;
import com.mycomp.cache.token.Attribute;
import com.mycomp.cache.token.Operands;
import com.mycomp.cache.token.Token;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.mycomp.cache.constant.KeywordLookup.OP_HKEY;

public class BooleanOperand extends Operands {
    private static final Logger logger = LogManager.getLogger(BooleanOperand.class);

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

    @Override
    public String toString() {
        String query = StringUtils.EMPTY;
        if(operatorEnum.equals(OperatorEnum.EXIST)){
            query+=OP_HKEY.get(operatorEnum)+" ";
        }else {
            query+= OP_HKEY.get(operatorEnum)+" "+value+" ";
        }
        return query;
    }
}
