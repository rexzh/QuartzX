package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.IRFIDDataDao;
import com.quartzx.datacollector.dao.IUserDataDao;
import com.quartzx.datacollector.model.RFIDData;
import com.quartzx.datacollector.model.UserData;
import com.quartzx.datacollector.utility.FilterRule;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zling on 6/29/2016.
 */
@Named
public class MessageService implements IMessageService {
    @Inject
    IRFIDDataDao _rfidDao;
    @Inject
    IUserDataDao _usrDao;

    public List<RFIDData> searchLatest(UserData user, FilterRule rule){

        UserData ud = _usrDao.getUserData(user.getUsername());

        if(ud.getPassword().equals(user.getPassword())) {
            return _rfidDao.searchLatest(ud.getDevices(), rule);
        } else {
            return new ArrayList<>();
        }
    }
}
