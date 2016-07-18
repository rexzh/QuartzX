package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.IRFIDDataDao;
import com.quartzx.datacollector.dao.IUserDataDao;
import com.quartzx.datacollector.model.Summarize;
import com.quartzx.datacollector.model.UserData;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by zling on 5/23/2016.
 */
@Named
public class SummaryService implements ISummaryService {
    @Inject
    private IRFIDDataDao _rfidDao;
    @Inject
    private IUserDataDao _usrDao;

    public Summarize summary(UserData user){
        UserData ud = _usrDao.getUserData(user.getUsername());
        Summarize s = new Summarize();
        if(ud.getPassword().equals(user.getPassword())) {
            long count = _rfidDao.count(ud.getDevices());
            s.setSum(count);
        } else {
            s.setSum(0);
        }

        return s;
    }
}
