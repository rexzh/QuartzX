package com.quartzx.datacollector.dao;

import com.quartzx.datacollector.model.RFIDData;

/**
 * Created by zling on 6/20/2016.
 */
public interface ICollectorDao {
    String persist(RFIDData data);
}
