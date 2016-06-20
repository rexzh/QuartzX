package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.SummaryDao;
import com.quartzx.datacollector.model.Summarize;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by zling on 5/23/2016.
 */
@Named
public class SummaryService implements ISummaryService {
    @Inject
    private SummaryDao _dao;

    public Summarize analysis(){
        Summarize s = new Summarize();
        s.setSum(_dao.count());
        return s;
    }
}
