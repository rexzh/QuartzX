package com.quartzx.datacollector.service;

import com.quartzx.datacollector.model.RFIDData;
import com.quartzx.datacollector.model.UserData;

import java.util.List;

/**
 * Created by zling on 6/29/2016.
 */
public interface IMessageService {
    List<RFIDData> searchLatest(UserData user);
}
