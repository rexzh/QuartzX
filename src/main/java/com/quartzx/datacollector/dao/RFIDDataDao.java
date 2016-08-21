package com.quartzx.datacollector.dao;

import com.mongodb.BasicDBObject;
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

    public List<String> persist(RFIDData[] array) {
        MongoCollection collection = mongoMgr.getCollection(MongoCollectionNames.Data);
        List<String> ids = new ArrayList<>();

        for(RFIDData data : array) {
            Document doc = new Document()
                    .append("deviceId", data.getId())
                    .append("tagId", data.getTagId())
                    .append("deviceTime", data.getDeviceTime())
                    .append("serverTime", ZonedDateTime.now().toInstant().toEpochMilli());

            collection.insertOne(doc);

            String id = doc.get("_id").toString();
            ids.add(id);
        }
        return ids;
    }

    public long count(List<String> devices) {
        MongoCollection collection = mongoMgr.getCollection(MongoCollectionNames.Data);
        return collection.count(new BasicDBObject("deviceId", new BasicDBObject("$in", devices)));
    }

    public List<RFIDData> dataInRange(List<String> devices, int seconds) {
        MongoCollection coll = mongoMgr.getCollection(MongoCollectionNames.Data);
        Date d = new Date();
        long ep = d.toInstant().toEpochMilli() - 1000 * seconds;

        FindIterable result = coll.find(Filters.and(
                Filters.gt("serverTime", ep),
                new BasicDBObject("deviceId", new BasicDBObject("$in", devices))
        ));
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

    public List<RFIDData> searchLatest(List<String> devices) {
        MongoCollection<RFIDData> coll = mongoMgr.getCollection(MongoCollectionNames.Data);
        FindIterable result = coll.find(new BasicDBObject("deviceId", new BasicDBObject("$in", devices)))
                .sort(new Document("serverTime", -1)).limit(10);

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

    public List<RFIDData> searchByTag(String tagId){
        MongoCollection coll = mongoMgr.getCollection(MongoCollectionNames.Data);
        FindIterable result = coll.find(Filters.eq("tagId", tagId));

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

    public List<RFIDData> searchByTimeRange(long start, long end){
        MongoCollection coll = mongoMgr.getCollection(MongoCollectionNames.Data);
        FindIterable result = coll.find(Filters.and(Filters.gt("serverTime", start), Filters.lt("serverTime", end)));

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
