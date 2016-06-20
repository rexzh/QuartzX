package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.ICollectorDao;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by zling on 6/20/2016.
 */
@Named
public class CollectorService implements ICollectorService {

    @Inject
    private ICollectorDao dao;

    public String persistRecord(String data){
        //TODO:Parse JSON and save

        return dao.persist(data);
    }
}
