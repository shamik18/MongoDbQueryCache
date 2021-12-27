package com.mycomp.mongo.gateway;

import com.mongodb.client.*;
import com.mycomp.cache.QueryCache;
import com.mycomp.models.HomeProperty;
import com.mycomp.mongo.connection.MongoConnection;
import com.mycomp.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.List;

public class MongoGateway {
    private static final Logger logger = LogManager.getLogger(MongoGateway.class);
    private MongoConnection mongoConnection = new MongoConnection();
    private String url;

    public MongoGateway(String url){
        this.url = url;
    }

    public List<HomeProperty> find(String query, String projection) {
        List<HomeProperty> list = new ArrayList<>();
        try (MongoClient mongoClient = mongoConnection.getMongoClient(this.url)) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("sample_airbnb");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("listingsAndReviews");
            logger.info("Executing mongodb query {}",query);
//            if(true)
//                return new ArrayList<>();
            FindIterable<Document> findIterable = mongoCollection.find(Document.parse(query));
            for (Document document : findIterable) {
                if(System.getProperty("suppress.mongodb") == null){
                    System.out.println(document.toJson());
                }
                list.add(JsonUtil.toPojo(document.toJson(), HomeProperty.class));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return list;
    }
}
