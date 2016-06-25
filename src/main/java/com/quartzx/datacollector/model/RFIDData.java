package com.quartzx.datacollector.model;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rex on 2016/6/25.
 */
public class RFIDData {
    private String _id;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    private String _tagId;

    public String getTagId() {
        return _tagId;
    }

    public void setTagId(String tagId) {
        _tagId = tagId;
    }

    //Note:These attribute/property are used for json serialize. Use getDeviceTime() in business logic.
    private String _timestamp;
    public String getTimestamp(){
        return _timestamp;
    }

    public void setTimestamp(String timestamp){
        _timestamp = timestamp;
    }

    public ZonedDateTime getDeviceTime(){
        ZonedDateTime d = ZonedDateTime.parse(_timestamp);
        return d;
    }

    private Date _serverTime;
    public Date getServerTime(){
        return _serverTime;
    }

    public void setServerTime(Date serverTime){
        _serverTime = serverTime;
    }
}
