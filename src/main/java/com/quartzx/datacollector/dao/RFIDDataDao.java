package com.quartzx.datacollector.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.quartzx.datacollector.model.RFIDData;
import com.quartzx.datacollector.utility.JsonConverter;
import com.quartzx.datacollector.utility.MongoCollectionNames;
import com.quartzx.datacollector.utility.MongoDBManager;
import org.bson.Document;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zling on 6/29/2016.
 */
@Named
public class RFIDDataDao implements IRFIDDataDao {
    @Inject
    MongoDBManager mongoMgr;

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

    public long count() {
        MongoCollection collection = mongoMgr.getCollection(MongoCollectionNames.Data);
        return collection.count();
    }

    public List<Long> dataInHour() {
        MongoCollection coll = mongoMgr.getCollection(MongoCollectionNames.Data);
        Date d = new Date();
        long ep = d.toInstant().toEpochMilli() - 1000 * 3600;

        FindIterable result = coll.find(Filters.gt("serverTime", ep));
        List<Long> list = new ArrayList<>();
        for (Object obj : result) {
            Document doc = (Document) obj;
            Long time = doc.getLong("serverTime");
            list.add(time);
        }

        return list;
    }

    public List<RFIDData> searchLatest() {
        MongoCollection<RFIDData> coll = mongoMgr.getCollection(MongoCollectionNames.Data);
        FindIterable result = coll.find().sort(new Document("serverTime", -1)).limit(10);

        List<RFIDData> list = new ArrayList<>();
        for (Object obj : result) {
            Document doc = (Document)obj;
            RFIDData data = new RFIDData();
            data.setId(doc.getString("deviceId"));
            data.setTagId(doc.getString("tagId"));
            data.setServerTime(doc.getLong("serverTime"));
            data.setDeviceTime(doc.getLong("deviceTime"));

            list.add(data);
        }

        return list;
    }
}
