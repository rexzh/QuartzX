package com.quartzx.datacollector.dao;

import com.quartzx.datacollector.model.Summarize;

import java.util.List;

/**
 * Created by zling on 5/23/2016.
 */
public interface ISummaryDao {
    long count();
    List<Long> dataInHour();
}
