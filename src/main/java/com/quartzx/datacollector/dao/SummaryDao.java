package com.quartzx.datacollector.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.quartzx.datacollector.utility.MongoCollectionNames;
import com.quartzx.datacollector.utility.MongoDBManager;
import org.bson.Document;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zling on 5/23/2016.
 */
@Named
public class SummaryDao implements ISummaryDao {

    @Inject
    MongoDBManager mongoMgr;
    public long count(){
        MongoCollection collection = mongoMgr.getCollection(MongoCollectionNames.Data);
        return collection.count();
    }

    public List<Long> dataInHour() {
        MongoCollection coll = mongoMgr.getCollection(MongoCollectionNames.Data);
        Date d = new Date();
        long ep = d.toInstant().toEpochMilli() - 1000 * 3600;

        FindIterable result = coll.find(Filters.gt("serverTime", ep));
        List<Long> list = new ArrayList<>();
        for(Object obj : result){
            Document doc = (Document) obj;
            Long time = doc.getLong("serverTime");
            list.add(time);
        }

        return list;
    }
}
