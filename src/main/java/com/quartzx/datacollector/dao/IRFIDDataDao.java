package com.quartzx.datacollector.dao;

import com.quartzx.datacollector.model.RFIDData;
import com.quartzx.datacollector.utility.FilterRule;

import java.util.List;

/**
 * Created by zling on 6/29/2016.
 */
public interface IRFIDDataDao {
    List<String> persist(RFIDData[] array);

    long count(List<String> devices);
    List<RFIDData> dataInRange(List<String> devices, int seconds, FilterRule rule);
    List<RFIDData> searchLatest(List<String> devices, FilterRule rule);

    List<RFIDData> searchByTag(String tagId);
    List<RFIDData> searchByTimeRange(long start, long end);
}
