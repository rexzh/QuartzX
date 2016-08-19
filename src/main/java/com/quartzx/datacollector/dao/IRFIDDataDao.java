package com.quartzx.datacollector.dao;

import com.quartzx.datacollector.model.RFIDData;

import java.util.List;

/**
 * Created by zling on 6/29/2016.
 */
public interface IRFIDDataDao {
    List<String> persist(RFIDData[] array);

    long count(List<String> devices);
    List<RFIDData> dataInRange(List<String> devices, int seconds);
    List<RFIDData> searchLatest(List<String> devices);
}
