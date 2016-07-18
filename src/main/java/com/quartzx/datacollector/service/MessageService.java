package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.IRFIDDataDao;
import com.quartzx.datacollector.model.RFIDData;
import com.quartzx.datacollector.model.UserData;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by zling on 6/29/2016.
 */
@Named
public class MessageService implements IMessageService {
    @Inject
    IRFIDDataDao _dao;

    public List<RFIDData> searchLatest(UserData user){
        //TODO:
        return _dao.searchLatest();
    }
}
