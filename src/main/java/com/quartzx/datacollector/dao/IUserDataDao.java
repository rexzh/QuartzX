package com.quartzx.datacollector.dao;

import com.quartzx.datacollector.model.UserData;

/**
 * Created by Rex on 2016/7/18.
 */
public interface IUserDataDao {
    UserData getUserData(String username);
}
