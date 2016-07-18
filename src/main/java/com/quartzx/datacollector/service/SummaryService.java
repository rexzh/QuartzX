package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.IRFIDDataDao;
import com.quartzx.datacollector.model.Summarize;
import com.quartzx.datacollector.model.UserData;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by zling on 5/23/2016.
 */
@Named
public class SummaryService implements ISummaryService {
    @Inject
    private IRFIDDataDao _dao;

    public Summarize summary(UserData user){
        //TODO:
        Summarize s = new Summarize();
        s.setSum(_dao.count());
        return s;
    }
}
