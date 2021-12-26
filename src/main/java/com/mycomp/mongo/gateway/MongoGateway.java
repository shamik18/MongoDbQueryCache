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

    public List<HomeProperty> find(String query, String projection) {
        List<HomeProperty> list = new ArrayList<>();
        try (MongoClient mongoClient = mongoConnection.getMongoClient()) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("sample_airbnb");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("listingsAndReviews");
            System.out.println(query);
            FindIterable<Document> findIterable = mongoCollection.find(Document.parse(query));
            for (Document document : findIterable) {
//                System.out.println(document.toJson());
                list.add(JsonUtil.toPojo(document.toJson(), HomeProperty.class));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return list;
    }
}
