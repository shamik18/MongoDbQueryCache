package com.mycomp.cache.token;

import com.mycomp.cache.enums.OperatorEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Set;

import static com.mycomp.cache.constant.KeywordLookup.KEY_OP;
import static com.mycomp.cache.constant.KeywordLookup.OP_HKEY;

public class Constrain implements Token{
    private String constrain_name;
    private Set<Token> child;


    public String getConstrain_name() {
        return constrain_name;
    }

    public void setConstrain_name(String constrain_name) {
        this.constrain_name = constrain_name;
    }

    public Set<Token> getChild() {
        return child;
    }

    public void setChild(Set<Token> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        String query = StringUtils.EMPTY;
        if(child != null){
            int total_count = child.size();
            int count = 0;
            for(Iterator<Token> it = child.iterator(); it.hasNext();){
                Token token = it.next();
                if(token != null)
                    query+=token.toString();
                if(count < (total_count-1)){
                    query+=" "+OP_HKEY.get(KEY_OP.get(constrain_name))+" ";
                }
                count++;
            }
        }

        return query;
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

        OperatorEnum thisOperatorEnum = KEY_OP.get(this.constrain_name);
        boolean check = false;
        if(thisOperatorEnum.equals(OperatorEnum.AND)){
            if(attribute != null) {
                check = andEqvCheckAttribute(attribute);
            }else if(constrain != null){
                check =andEqvCheckConstrain(constrain);
            }
        }else if(thisOperatorEnum.equals(OperatorEnum.OR)){
            if(attribute != null) {
                check = orEqvCheckAttribute(attribute);
            }else if(constrain != null){
                check = orEqvCheckConstrain(constrain);
            }
        }else if(thisOperatorEnum.equals(OperatorEnum.IN)){
            if(attribute != null) {
                check = inEqvCheckAttribute(attribute);
            }else if(constrain != null){
                check = inEqvCheckConstrain(constrain);
            }
        }


        return check;
    }

    private boolean inEqvCheckConstrain(Constrain constrain) {
        return false;
    }

    private boolean inEqvCheckAttribute(Attribute attribute) {
        return false;
    }

    private boolean orEqvCheckConstrain(Constrain constrain) {
        OperatorEnum otherOperatorEnum = KEY_OP.get(this.constrain_name);
        if(otherOperatorEnum.equals(OperatorEnum.AND)){
            //every token of other should match any token on this.
            for(Token token : constrain.child){
              if(this.child.stream().anyMatch(token1 -> token1.isEquivalent(token))) {
                  return true;
              }
            }
            return true;
        }else if(otherOperatorEnum.equals(OperatorEnum.OR)){
            //every token of other should match any token on this and number of token should equal.
            boolean isMatch;
            for(Token token : constrain.child){
                isMatch = this.child.stream().anyMatch(token1 -> token1.isEquivalent(token));
                if(!isMatch)
                    return false;
            }
            return true;
        }
        return false;
    }

    private boolean orEqvCheckAttribute(Attribute attribute) {
        return this.child.stream().anyMatch(token -> token.isEquivalent(attribute));
    }

    private boolean andEqvCheckConstrain(Constrain constrain) {
        OperatorEnum otherOperatorEnum = KEY_OP.get(this.constrain_name);
        if(otherOperatorEnum.equals(OperatorEnum.AND)){
            //every token of other should match any token on this.
            boolean isMatch = false;
            for(Token token : constrain.child){
                isMatch = this.child.stream().anyMatch(token1 -> token1.isEquivalent(token));
                if(!isMatch)
                    return isMatch;
            }
            return true;
        }else if(otherOperatorEnum.equals(OperatorEnum.OR)){
            return false;
        }
        return false;
    }

    private boolean andEqvCheckAttribute(Attribute attribute) {
        return false;
    }
}
