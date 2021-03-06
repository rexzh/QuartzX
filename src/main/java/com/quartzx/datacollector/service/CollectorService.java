package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.IRFIDDataDao;
import com.quartzx.datacollector.model.RFIDData;
import com.quartzx.datacollector.utility.JsonConverter;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by zling on 6/20/2016.
 */
@Named
public class CollectorService implements ICollectorService {

    @Inject
    private IRFIDDataDao dao;

    public List<String> persistRecords(String json) {
        RFIDData[] data = JsonConverter.toObject(json, RFIDData[].class);

        return dao.persist(data);
    }
}
