package com.mycomp.cache.constant;

import com.mycomp.cache.enums.ClauseType;
import com.mycomp.cache.enums.OperatorEnum;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KeywordLookup {
    public static final Map<String, ClauseType> KEYWORD_CLAUSE = new HashMap<>();
    public static final Set<String> ComjuctureSet = new HashSet<>();
    public static final Set<String> OperatorSet = new HashSet<>();
    public static final Map<String,String> FIELD_MAPPER = new HashMap<>();
    public static final Map<String, OperatorEnum> KEY_OP= new HashMap<>();
    public static final Map<OperatorEnum,String> OP_HKEY = new HashMap<>();



    static {
        ComjuctureSet.add("$and");
        ComjuctureSet.add("$or");


        OperatorSet.add("$regex");
        OperatorSet.add("$gt");
        OperatorSet.add("$lt");
        OperatorSet.add("$exists");
        OperatorSet.add("$eq");

    }

    static {
        KEYWORD_CLAUSE.put("$match",ClauseType.WHERE);
        KEYWORD_CLAUSE.put("$group",ClauseType.GROUPBY);
        KEYWORD_CLAUSE.put("$count",ClauseType.ARRG_FUNC);
        KEYWORD_CLAUSE.put("$sort",ClauseType.SORT);
        KEYWORD_CLAUSE.put("$project",ClauseType.SELECT);
    }

    static {
        FIELD_MAPPER.put("_id", "hp.id");
        FIELD_MAPPER.put("access", "hp.access");
        FIELD_MAPPER.put("accommodates","hp.accommodates");
        FIELD_MAPPER.put("bed_type","hp.bedType");
        FIELD_MAPPER.put("bedrooms","hp.bedrooms");
        FIELD_MAPPER.put("beds","hp.beds");
        FIELD_MAPPER.put("description","hp.description");
        FIELD_MAPPER.put("house_rules","hp.houseRules");
        FIELD_MAPPER.put("interaction","hp.interaction");
        FIELD_MAPPER.put("listing_url","hp.listingUrl");
        FIELD_MAPPER.put("maximum_nights","hp.maximumNights");
        FIELD_MAPPER.put("minimum_nights","hp.minimumNights");
        FIELD_MAPPER.put("name","hp.name");
        FIELD_MAPPER.put("neighborhood_overview","hp.neighborhoodOverview");
        FIELD_MAPPER.put("notes","hp.notes");
        FIELD_MAPPER.put("number_of_reviews","hp.numberOfReviews");
        FIELD_MAPPER.put("price","hp.price" );
        FIELD_MAPPER.put("property_type","hp.propertyType");
        FIELD_MAPPER.put("room_type","hp.roomType");
        FIELD_MAPPER.put("space","hp.space");
        FIELD_MAPPER.put("summary","hp.summary");
        FIELD_MAPPER.put("transit","hp.transit");
    }

    static {
        KEY_OP.put("$eq",OperatorEnum.EQ);
        KEY_OP.put("$regex",OperatorEnum.LIKE);
        KEY_OP.put("$gt",OperatorEnum.GT);
        KEY_OP.put("$lt",OperatorEnum.LT);
        KEY_OP.put("$or",OperatorEnum.OR);
        KEY_OP.put("$and",OperatorEnum.AND);
        KEY_OP.put("$exists",OperatorEnum.EXIST);

        OP_HKEY.put(OperatorEnum.EQ,"=");
        OP_HKEY.put(OperatorEnum.LIKE,"like");
        OP_HKEY.put(OperatorEnum.GT,">");
        OP_HKEY.put(OperatorEnum.LT,"<");
        OP_HKEY.put(OperatorEnum.OR,"or");
        OP_HKEY.put(OperatorEnum.AND,"and");
        OP_HKEY.put(OperatorEnum.EXIST,"is not null");


    }
}
