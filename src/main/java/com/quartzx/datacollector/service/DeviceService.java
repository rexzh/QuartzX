package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.IUserDataDao;
import com.quartzx.datacollector.dao.UserDataDao;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by zling on 7/27/2016.
 */
@Named
public class DeviceService implements IDeviceService {
    @Inject
    IUserDataDao dao;

    public Boolean validate(String deviceId) {
        List<String> devices = dao.getDeviceList();
        return devices.contains(deviceId);
    }
}
