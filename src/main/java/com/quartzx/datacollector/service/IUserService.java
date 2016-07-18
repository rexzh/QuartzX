package com.quartzx.datacollector.service;

import com.quartzx.datacollector.model.UserData;

/**
 * Created by Rex on 2016/7/18.
 */
public interface IUserService {
    UserData authenticate(String data);
}
