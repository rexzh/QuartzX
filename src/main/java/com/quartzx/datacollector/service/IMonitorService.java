package com.quartzx.datacollector.service;

import com.quartzx.datacollector.utility.FilterRule;
import com.quartzx.datacollector.model.MonitorData;
import com.quartzx.datacollector.model.UserData;

/**
 * Created by zling on 6/29/2016.
 */
public interface IMonitorService {
    MonitorData analysis(UserData user, int seconds, FilterRule rule);
}
