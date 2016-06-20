package com.quartzx.datacollector.utility;


import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;


import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;

/**
 * Created by zling on 6/15/2016.
 */
@Named
public class MongoManager {
    @Inject
    private AppProperty appProperty;
    private MongoDatabase db;

    @PostConstruct
    public void init() {
        String connectionDescriptor = String.format("host: %s, port: %s, db: %s", appProperty.getMongoDbServerHost(), appProperty.getMongoDbServerPort(), appProperty.getMongoDbName());

        try {
            // To connect to a replica set, with auto-discovery of the primary, supply a seed list of members
            MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress(appProperty.getMongoDbServerHost(), appProperty.getMongoDbServerPort())));
            db = mongoClient.getDatabase(appProperty.getMongoDbName());
        } catch (Exception e) {
            throw new MongoException(String.format("Failed to connect to MongoDB server - %s", connectionDescriptor), e);
        }
    }

    public MongoDatabase getDatabase() {
        return db;
    }
}
