package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.IUserDataDao;
import com.quartzx.datacollector.model.UserData;
import com.quartzx.datacollector.utility.JsonConverter;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Rex on 2016/7/18.
 */
@Named
public class UserService implements IUserService {
    @Inject
    IUserDataDao _dao;

    public UserData authenticate(String json) {
        UserData user = JsonConverter.toObject(json, UserData.class);

        UserData data = _dao.getUserData(user.getUsername());

        Boolean success = data.getPassword().equals(user.getPassword());
        if(success) {
            user.setSuccess(true);
            user.setDevices(data.getDevices());
        }
        return user;
    }
}
