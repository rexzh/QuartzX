package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.IRFIDDataDao;
import com.quartzx.datacollector.dao.IUserDataDao;
import com.quartzx.datacollector.model.RFIDData;
import com.quartzx.datacollector.model.UserData;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rex on 2016/8/21.
 */
@Named
public class SearchService implements ISearchService {

    @Inject
    private IRFIDDataDao _dao;

    @Inject
    private IUserDataDao _usrDao;

    public List<RFIDData> searchByTimeRange(UserData u, Long start, Long end) {
        UserData ud = _usrDao.getUserData(u.getUsername());

        List<RFIDData> array = _dao.searchByTimeRange(start, end);
        List<RFIDData> result = new ArrayList<>();
        for(RFIDData data : array){
            if(ud.getDevices().contains(data.getId()))
                result.add(data);
        }
        return result;
    }

    public List<RFIDData> searchByTagId(UserData u, String tagId) {
        UserData ud = _usrDao.getUserData(u.getUsername());

        List<RFIDData> array = _dao.searchByTag(tagId);
        List<RFIDData> result = new ArrayList<>();
        for(RFIDData data : array){
            if(ud.getDevices().contains(data.getId()))
                result.add(data);
        }
        return result;
    }
}
