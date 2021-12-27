package com.mycomp.cache.token.primitive;

import com.mycomp.cache.enums.OperatorEnum;
import com.mycomp.cache.token.Operands;
import com.mycomp.cache.token.Token;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.mycomp.cache.constant.KeywordLookup.OP_HKEY;

public class IntOperand extends Operands {
    private static final Logger logger = LogManager.getLogger(IntOperand.class);

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
                return this.value.intValue() >= intOperand.value.intValue();
            }else if(this.operatorEnum.equals(OperatorEnum.LT)){
                return this.value.intValue() <= intOperand.value.intValue();
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String query = StringUtils.EMPTY;
        if(operatorEnum.equals(OperatorEnum.EQ)) {
            query +=OP_HKEY.get(OperatorEnum.EQ)+""+value+" ";
        }else if(operatorEnum.equals(OperatorEnum.GT)) {
            query +=OP_HKEY.get(OperatorEnum.GT)+""+value+" ";
        }else if(operatorEnum.equals(OperatorEnum.LT)){
            query +=OP_HKEY.get(OperatorEnum.LT)+""+value+" ";
        }else if(operatorEnum.equals(OperatorEnum.GTE)) {
            query +=OP_HKEY.get(OperatorEnum.GTE)+""+value+" ";
        }else if(operatorEnum.equals(OperatorEnum.LTE)) {
            query +=OP_HKEY.get(OperatorEnum.LTE)+""+value+" ";
        }
        return query;
    }
}
