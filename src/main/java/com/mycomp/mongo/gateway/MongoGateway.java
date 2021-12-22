package com.mycomp.mongo.gateway;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.*;
import com.mycomp.models.HomeProperty;
import com.mycomp.mongo.connection.MongoConnection;
import com.mycomp.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MongoGateway {
    private MongoConnection mongoConnection = new MongoConnection();

    public List<HomeProperty> find(String query, String projection){
        List<HomeProperty> list = new ArrayList<>();
        MongoClient mongoClient = mongoConnection.getMongoClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("sample_airbnb");
        MongoCollection<Document> mongoCollection= mongoDatabase.getCollection("listingsAndReviews");
        FindIterable<Document> findIterable = mongoCollection.find(Document.parse(query));
        for(Document document:findIterable){
            System.out.println(document.toJson());
            list.add(JsonUtil.toPojo(document.toJson(),HomeProperty.class));
        }
        return list;
    }

    public void aggregate(List<String> aggregateStr){
        List<Bson> listBson = new ArrayList<>();
        for(String str: aggregateStr){
            Bson bson = Document.parse(str);
            listBson.add(bson);
        }
        try(MongoClient mongoClient = mongoConnection.getMongoClient()){
            MongoDatabase mongoDatabase = mongoClient.getDatabase("sample_airbnb");
            MongoCollection<Document> mongoCollection= mongoDatabase.getCollection("listingsAndReviews");
            AggregateIterable<Document> documentAggregateIterable = mongoCollection.aggregate(listBson);
            JsonWriterSettings settings = JsonWriterSettings.builder().outputMode(JsonMode.RELAXED).build();
            for(Document document:documentAggregateIterable){

                System.out.println(document.toJson(settings));
                System.out.println();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
