package com.mycomp.cache.token;

import com.mycomp.cache.enums.OperatorEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

import static com.mycomp.cache.constant.KeywordLookup.FIELD_MAPPER;
import static com.mycomp.cache.constant.KeywordLookup.KEY_OP;

public class Attribute implements Token{
    private static final Logger logger = LogManager.getLogger(Attribute.class);

    private String attr_name;
    private Set<Operands> operandsSet;

    public String getAttr_name() {
        return attr_name;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    public Set<Operands> getOperandsSet() {
        return operandsSet;
    }

    public void setOperandsSet(Set<Operands> operandsSet) {
        this.operandsSet = operandsSet;
    }

    @Override
    public boolean isEquivalent(Token token) {
        Attribute attribute = null;
        Constrain constrain = null;
        if((token instanceof  Attribute)){
            attribute = (Attribute) token;
        }
        if(token instanceof Constrain){
            constrain = (Constrain) token;
        }
        if(attribute==null && token==null)
            return false;
        if(attribute != null){
            if(attribute.attr_name.equals(this.attr_name)){
                if(this.operandsSet != null && attribute.operandsSet != null){
                    for(Operands operands : this.operandsSet){
                        boolean isMatched = attribute.operandsSet.stream().anyMatch(operands1 -> operands1.isEquivalent(operands));
                        if(!isMatched)
                            return false;
                    }
                    return true;
                }
            }
        }

        if(constrain != null){
            OperatorEnum otherOperatorEnum = KEY_OP.get(constrain.getConstrain_name());
            if(otherOperatorEnum.equals(OperatorEnum.AND)){
              return constrain.getChild().stream().anyMatch(token1 -> token1.isEquivalent(this));
            }else if(otherOperatorEnum.equals(OperatorEnum.OR)){
               return false;
            }else if(otherOperatorEnum.equals(OperatorEnum.IN)){
               return false;
            }
        }

        return false;
    }
    @Override
    public String toString() {
        String hsqlQuery = StringUtils.EMPTY;
        hsqlQuery+=FIELD_MAPPER.get(attr_name);
        if(operandsSet != null){
            for(Operands operands : operandsSet){
                if(operands != null){
                    hsqlQuery+=" "+operands.toString();
                }
            }
        }

        return hsqlQuery;
    }
}
