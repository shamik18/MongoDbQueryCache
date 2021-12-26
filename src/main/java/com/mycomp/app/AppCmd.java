package com.mycomp.app;

import com.mycomp.cache.CacheResult;
import com.mycomp.cache.MongoClientWithCache;
import com.mycomp.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;

public class AppCmd {
    public static void main(String[] args) {
        CacheResult cacheResult = null;
        MongoClientWithCache mongoClientWithCache = new MongoClientWithCache();
        String qry1 = "collection.aggregate([\n" +
                "                        {\"$match\" : {\n" +
                "                                        \"$or\" : [\n" +
                "                                                {\"name\": {\"$regex\":\"Beach\"} },\n" +
                "                                                {\"property_type\":\"House\"}\n" +
                "                                        ]\n" +
                "                        }},\n" +
                "                        { \"$project\": { \"name\": 1, \"_id\": 0} }\n" +
                "        ])\n" +
                "\t\t";
        System.out.println(StringUtils.deleteWhitespace(qry1));
        cacheResult = mongoClientWithCache.excuteQuery(qry1);

        String qry2 = "collection.aggregate([\n" +
                "                {\n" +
                "                        \"$match\" : {\n" +
                "                                        \"$or\" : [\n" +
                "                                                {\"property_type\":\"House\"}\n" +
                "                                        ]\n" +
                "                        }\n" +
                "\n" +
                "                },\n" +
                "                { \"$project\": { \"name\": 1, \"_id\": 0} }\n" +
                "        ])";
        System.out.println(StringUtils.deleteWhitespace(qry2));
        cacheResult = mongoClientWithCache.excuteQuery(qry2);

        String qry3 = "collection.aggregate([ \n" +
                "                {\n" +
                "                        \"$match\" : {\n" +
                "                                        \"name\": {\"$regex\":\"Beach\"}, \n" +
                "                                        \"property_type\": {\"$exists\":true, \"$eq\": \"House\"}, \n" +
                "                                        \"accommodates\": {\"$gt\": 6 }\n" +
                "                                        \n" +
                "                        }\n" +
                "\n" +
                "                }, \n" +
                "                {        \n" +
                "                        \"$count\":  \"number_of_records\"  \n" +
                "                }\n" +
                "                 \n" +
                "        ])";
        System.out.println(StringUtils.deleteWhitespace(qry3));
        cacheResult = mongoClientWithCache.excuteQuery(qry3);

        String qry4 = "collection.find( { \"$or\" : [ {\"name\":{\"$regex\":\"Beach\"} } , { \"property_type\":\"House\"} ] }, {\"name\":1,\"_id\":0} ).sort(\"name\",-1).limit(10)";
        System.out.println(StringUtils.deleteWhitespace(qry4));
        cacheResult = mongoClientWithCache.excuteQuery(qry4);
        if(cacheResult.isSingleResult()){
            System.out.println(cacheResult.getValue());
        }else{
            cacheResult.getHomeProperties().forEach(item -> System.out.println(JsonUtil.toJsonString(item)));
        }
//        String qry5 = "collection.find( { \"$text\" : { \"$search\" : \"beach\" } }, {\"name\":1,\"accommodates\":1,\"_id\":0}).sort(\"accommodates\", -1).limit(10)";
//        System.out.println(StringUtils.deleteWhitespace(qry4));
//        cacheResult = mongoClientWithCache.excuteQuery(qry5);
        String qry6 = "collection.aggregate([ \n" +
                "\t\t{\n" +
                "\t\t\t\t\"$match\" : {\n" +
                "\t\t\t\t\t\t\t\t\"$and\" : [\n" +
                "\t\t\t\t\t\t\t\t{\"name\": {\"$regex\":\"Beach\"} }, \n" +
                "\t\t\t\t\t\t\t\t{\"accommodates\": {\"$gt\": 6 }}\n" +
                "\t\t\t\t\t\t\t\t]\n" +
                "\t\t\t\t}\n" +
                "\t\t},\n" +
                "\n" +
                "\t\t{\n" +
                "\t\t\t\t\"$group\" : {\n" +
                "\t\t\t\t\t\t\t\t\"_id\": {\n" +
                "\t\t\t\t\t\t\t\t\t\t \"property_type\": \"$property_type\"\n" +
                "\t\t\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\t\t\"count\":  {\"$sum\":1} \n" +
                "\t\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\t\"$sort\" : { \n" +
                "\t\t\t\t\t\t \"count\" : -1\n" +
                "\t\t\t\t}\n" +
                "\t\t}\n" +
                "\t\t \n" +
                "])";
        System.out.println(StringUtils.deleteWhitespace(qry6));
        cacheResult = mongoClientWithCache.excuteQuery(qry6);

    }
}
