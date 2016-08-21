package com.quartzx.datacollector.service;

import com.quartzx.datacollector.model.RFIDData;
import com.quartzx.datacollector.model.UserData;

import java.util.List;

/**
 * Created by Rex on 2016/8/21.
 */
public interface ISearchService {
    List<RFIDData> searchByTimeRange(UserData u, Long start, Long end);
    List<RFIDData> searchByTagId(UserData u, String tagId);
}
