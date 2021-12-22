package com.mycomp.app;

import com.mycomp.cache.MongoClientWithCache;
import com.mycomp.mongo.gateway.MongoGateway;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class AppCmd {
    public static void main(String[] args) {
        MongoClientWithCache mongoClientWithCache = new MongoClientWithCache();
        //test the where clause
        String str1 = "{ \"$or\" : [ {\"name\":{\"$regex\":\"Beach\"} } , { \"property_type\":\"House\"} ] }";
        mongoClientWithCache.find(str1);

//        MongoGateway mongoGateway = new MongoGateway();
//        String query1 = "{ \"property_type\":\"House\"}";
//        String query2 = "{ \"$or\" : [ {\"name\":{\"$regex\":\"Beach\"} } , { \"property_type\":\"House\"} ] }";
//        List<HomeProperty> list = mongoGateway.find(query2,null);
//        System.out.println(list.size());
//        String aggrStr ="";
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonParser jsonParser = null;
//        try {
//            jsonParser = objectMapper.getFactory().createParser(aggrStr);
//            JsonNode jsonNode = objectMapper.readTree(jsonParser);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        String aggrStr ="[\n" +
//                "                        {\"$match\" : {\n" +
//                "                                        \"$or\" : [\n" +
//                "                                                {\"name\": {\"$regex\":\"Beach\"} },\n" +
//                "                                                {\"property_type\":\"House\"}\n" +
//                "                                        ]\n" +
//                "                        }},\n" +
//                "                        { \"$project\": { \"name\": 1, \"_id\": 0} }\n" +
//                "        ]";
//        ;
//        String token = StringUtils.deleteWhitespace(aggrStr);
//        List<String> tokens = Arrays.asList(token);
//        tokens.forEach(System.out::println);
//        str1 = "{\"$match\":{\"$or\":[{\"name\":{\"$regex\":\"Beach\"}},{\"property_type\":\"House\"}]}},";
////        String str2 ="{\"$project\":{\"name\":1,\"_id\":0}}";
//        List<String> argStr = Arrays.asList(str1);
//        mongoGateway.aggregate(argStr);
//
//        String aggrStr2 = "[\n" +
//                "                {\n" +
//                "                        \"$match\" : {\n" +
//                "                                        \"$or\" : [\n" +
//                "                                                {\"property_type\":\"House\"}\n" +
//                "                                        ]\n" +
//                "                        },\n" +
//                "                },\n" +
//                "                { \"$project\": { \"name\": 1, \"_id\": 0} }\n" +
//                "        ]";
//        token = StringUtils.deleteWhitespace(aggrStr2);


    }
}
