package com.quartzx.datacollector.dao;

import com.quartzx.datacollector.model.Summarize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

/**
 * Created by zling on 5/23/2016.
 */
@Named
public class SummaryDao implements ISummaryDao {
    private static final Logger logger = LoggerFactory.getLogger(SummaryDao.class);

    public Summarize analysis(){
        logger.debug("=================Entry");
        Summarize s = new Summarize();
        s.setAverage(100);
        s.setSum(300);
        logger.debug(s.toString());
        logger.debug("=================Done");
        return s;
    }
}
