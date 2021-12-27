package com.mycomp.mongo.connection;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mycomp.models.HomeProperty;
import com.mycomp.util.JsonUtil;
import org.bson.Document;

public class MongoConnection {
    private MongoClient mongoClient;
    private static final String _URL ="mongodb+srv://admin:admin@cluster0.lpfj2.mongodb.net/sample_airbnb?retryWrites=true&w=majority";

    public MongoClient getMongoClient(String url) {
//        ConnectionString connectionString = new ConnectionString(_URL);
        ConnectionString connectionString = new ConnectionString(url);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        mongoClient = MongoClients.create(mongoClientSettings);
        return mongoClient;
    }

    public static void main(String[] args) {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://admin:admin@cluster0.lpfj2.mongodb.net/sample_airbnb?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("sample_airbnb");
        MongoCollection<Document> collection = database.getCollection("listingsAndReviews");
        String str = "{\"$or\":[{\"name\":{\"$regex\":\"Beach\"}},{\"property_type\":\"House\"}]}";
        FindIterable<Document> findIterable = collection.find(Document.parse(str)) ;

        for(Document document:findIterable){
            System.out.println(document.toJson());
        }
    }

}
