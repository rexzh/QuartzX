package com.quartzx.datacollector.service;

import com.quartzx.datacollector.model.Summarize;
import com.quartzx.datacollector.model.UserData;

/**
 * Created by zling on 5/23/2016.
 */
public interface ISummaryService {
    Summarize summary(UserData user);
}
