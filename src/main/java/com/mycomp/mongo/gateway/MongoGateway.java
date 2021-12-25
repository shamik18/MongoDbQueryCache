package com.mycomp.mongo.gateway;

import com.mongodb.client.*;
import com.mycomp.models.HomeProperty;
import com.mycomp.mongo.connection.MongoConnection;
import com.mycomp.util.JsonUtil;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.List;

public class MongoGateway {
    private MongoConnection mongoConnection = new MongoConnection();

    public List<HomeProperty> find(String query, String projection){
        List<HomeProperty> list = new ArrayList<>();
        try(MongoClient mongoClient = mongoConnection.getMongoClient()){
            MongoDatabase mongoDatabase = mongoClient.getDatabase("sample_airbnb");
            MongoCollection<Document> mongoCollection= mongoDatabase.getCollection("listingsAndReviews");
            System.out.println(query);
            String str = "{\"$or\":[{\"name\":{\"$regex\":\"Beach\"}},{\"property_type\":\"House\"}]}";

            FindIterable<Document> findIterable = mongoCollection.find(Document.parse(str));
            for(Document document:findIterable){
                System.out.println(document.toJson());
                list.add(JsonUtil.toPojo(document.toJson(),HomeProperty.class));
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }

        return list;
    }

    public List<HomeProperty> aggregate(List<Bson> listBson){
        List<HomeProperty> list = new ArrayList<>();
        try(MongoClient mongoClient = mongoConnection.getMongoClient()){
            MongoDatabase mongoDatabase = mongoClient.getDatabase("sample_airbnb");
            MongoCollection<Document> mongoCollection= mongoDatabase.getCollection("listingsAndReviews");
            AggregateIterable<Document> documentAggregateIterable = mongoCollection.aggregate(listBson);
            JsonWriterSettings settings = JsonWriterSettings.builder().outputMode(JsonMode.RELAXED).build();
            for(Document document:documentAggregateIterable){
                System.out.println(document.toJson());
                list.add(JsonUtil.toPojo(document.toJson(),HomeProperty.class));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
