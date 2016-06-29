package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.IRFIDDataDao;
import com.quartzx.datacollector.model.RFIDData;

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

    public List<RFIDData> searchLatest(){
        return _dao.searchLatest();
    }
}
