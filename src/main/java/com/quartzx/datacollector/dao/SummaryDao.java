package com.quartzx.datacollector.dao;

import com.quartzx.datacollector.model.Summarize;

import javax.inject.Named;

/**
 * Created by zling on 5/23/2016.
 */
@Named
public class SummaryDao implements ISummaryDao {
    public Summarize analysis(){
        Summarize s = new Summarize();
        s.setAverage(100);
        s.setSum(300);
        return s;
    }
}
