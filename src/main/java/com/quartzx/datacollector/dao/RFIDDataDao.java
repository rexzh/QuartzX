package com.quartzx.datacollector.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.quartzx.datacollector.model.RFIDData;
import com.quartzx.datacollector.utility.FilterRule;
import com.quartzx.datacollector.utility.JsonConverter;
import com.quartzx.datacollector.utility.MongoCollectionNames;
import com.quartzx.datacollector.utility.MongoDBManager;
import org.bson.Document;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.ZonedDateTime;
import java.util.*;

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

    public List<RFIDData> dataInRange(List<String> devices, int seconds, FilterRule rule) {
        //TODO:
        MongoCollection coll = mongoMgr.getCollection(MongoCollectionNames.Data);
        Date d = new Date();
        long ep = d.toInstant().toEpochMilli() - 1000 * (long)seconds;

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

    public List<RFIDData> searchLatest(List<String> devices, FilterRule rule) {
        MongoCollection coll = mongoMgr.getCollection(MongoCollectionNames.Data);
        List<RFIDData> list = new ArrayList<>();

        switch (rule) {
            case None:
                FindIterable result = coll.find(new BasicDBObject("deviceId", new BasicDBObject("$in", devices)))
                        .sort(new Document("serverTime", -1)).limit(10);


                for (Object obj : result) {
                    Document doc = (Document) obj;
                    RFIDData data = new RFIDData();
                    data.setId(doc.getString("deviceId"));
                    data.setTagId(doc.getString("tagId"));
                    data.setServerTime(doc.getLong("serverTime"));
                    data.setDeviceTime(doc.getLong("deviceTime"));

                    list.add(data);
                }
                break;

            case KeepMin:
            case KeepMax:
                Map<String, Object> dbObjIdMap = new HashMap<>();
                dbObjIdMap.put("deviceId", "$deviceId");
                dbObjIdMap.put("tagId", "$tagId");
                BasicDBObject groupFields = new BasicDBObject( "_id", new BasicDBObject(dbObjIdMap));

                String aggOperator = (rule==FilterRule.KeepMax) ? "$max" : "$min";
                AggregateIterable<Document> iterable = coll.aggregate(
                        Arrays.asList(
                                new BasicDBObject("$group", groupFields.append(
                                        "time", new BasicDBObject(aggOperator, "$serverTime"))),
                                new BasicDBObject("$match", new BasicDBObject("deviceId", new BasicDBObject("$in", devices))),
                                new BasicDBObject("$sort", new BasicDBObject("time", -1))
                        )
                );
                for(Document d : iterable) {
                    RFIDData data = new RFIDData();

                    Document key = (Document) d.get("_id");
                    data.setId(key.getString("deviceId"));
                    data.setTagId(key.getString("tagId"));
                    data.setServerTime(d.getLong("time"));

                    list.add(data);
                }
                break;
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
