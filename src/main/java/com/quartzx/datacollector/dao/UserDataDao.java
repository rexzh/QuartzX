package com.quartzx.datacollector.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.quartzx.datacollector.model.UserData;
import com.quartzx.datacollector.utility.MongoCollectionNames;
import com.quartzx.datacollector.utility.MongoDBManager;

import javax.inject.Inject;
import javax.inject.Named;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rex on 2016/7/18.
 */
@Named
public class UserDataDao implements IUserDataDao {
    @Inject
    MongoDBManager mongoMgr;

    public UserData getUserData(String username){
        MongoCollection coll = mongoMgr.getCollection(MongoCollectionNames.Users);

        UserData data = new UserData();

        Document doc = (Document)coll.find(Filters.eq("name", username)).first();
        if(doc == null) {
            data.setSuccess(false);
        } else {
            data.setUsername(username);
            data.setPassword(doc.getString("password"));
            ArrayList arr = (ArrayList)doc.get("devices");
            for(Object device : arr) {
                data.getDevices().add((String) device);
            }
        }

        return data;
    }

    public List<String> getDeviceList(){
        List<String> devices = new ArrayList<>();
        MongoCollection coll = mongoMgr.getCollection(MongoCollectionNames.Users);
        FindIterable result = coll.find();
        for (Object obj : result) {
            Document doc = (Document)obj;
            ArrayList arr = (ArrayList)doc.get("devices");

            for(Object device : arr){
                devices.add((String)device);
            }
        }
        return devices;
    }
}
