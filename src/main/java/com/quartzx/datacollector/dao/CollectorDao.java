package com.quartzx.datacollector.dao;

import com.mongodb.client.MongoCollection;
import com.quartzx.datacollector.utility.MongoCollectionNames;
import com.quartzx.datacollector.utility.MongoDBManager;
import org.bson.Document;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by zling on 6/20/2016.
 */
@Named
public class CollectorDao implements ICollectorDao {
    @Inject
    private MongoDBManager mongoMgr;

    public String persist(String data) {
        MongoCollection collection = mongoMgr.getCollection(MongoCollectionNames.Data);
        Document doc = new Document()
                .append("data", data);
        collection.insertOne(doc);

        return doc.get("_id").toString();
    }
}
