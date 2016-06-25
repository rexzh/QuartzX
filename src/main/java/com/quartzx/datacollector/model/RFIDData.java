package com.quartzx.datacollector.model;

import com.quartzx.datacollector.CommonException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
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

    public void setTimestamp(String timestamp) {
        _timestamp = timestamp;
    }

    public long getDeviceTime() {
        ZonedDateTime zdt = ZonedDateTime.parse(_timestamp);
        return zdt.toInstant().toEpochMilli();
    }

    private long _serverTime;
    public long getServerTime() {
        return _serverTime;
    }

    public void setServerTime(long serverTime) {
        _serverTime = serverTime;
    }
}
