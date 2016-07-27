package com.quartzx.datacollector.dao;

import com.quartzx.datacollector.model.UserData;

import java.util.List;

/**
 * Created by Rex on 2016/7/18.
 */
public interface IUserDataDao {
    UserData getUserData(String username);
    List<String> getDeviceList();
}
