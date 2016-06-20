package com.quartzx.datacollector.dao;

import com.mongodb.client.MongoCollection;
import com.quartzx.datacollector.utility.MongoCollectionNames;
import com.quartzx.datacollector.utility.MongoDBManager;

import javax.inject.Inject;
import javax.inject.Named;

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
}
