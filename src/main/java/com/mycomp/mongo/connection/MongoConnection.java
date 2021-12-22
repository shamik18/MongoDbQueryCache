package com.mycomp.mongo.connection;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoConnection {
    private MongoClient mongoClient;
    private static final String _URL ="mongodb+srv://mongo:test@cluster0.lpfj2.mongodb.net/sample_airbnb?retryWrites=true&w=majority";

    public MongoClient getMongoClient() {
        ConnectionString connectionString = new ConnectionString(_URL);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        mongoClient = MongoClients.create(mongoClientSettings);
        return mongoClient;
    }

}
