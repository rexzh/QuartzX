package com.quartzx.datacollector.dao;

import com.mongodb.client.MongoCollection;
import com.quartzx.datacollector.model.RFIDData;
import com.quartzx.datacollector.utility.MongoCollectionNames;
import com.quartzx.datacollector.utility.MongoDBManager;
import org.bson.Document;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by zling on 6/20/2016.
 */
@Named
public class CollectorDao implements ICollectorDao {
    @Inject
    private MongoDBManager mongoMgr;

    public String persist(RFIDData data) {
        MongoCollection collection = mongoMgr.getCollection(MongoCollectionNames.Data);
        Document doc = new Document()
                .append("deviceId", data.getId())
                .append("tagId", data.getTagId())
                .append("deviceTime", data.getDeviceTime())
                .append("serverTime", ZonedDateTime.now().toInstant().toEpochMilli());

        collection.insertOne(doc);

        return doc.get("_id").toString();
    }
}
