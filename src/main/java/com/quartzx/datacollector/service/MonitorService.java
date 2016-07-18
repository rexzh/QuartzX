package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.IRFIDDataDao;
import com.quartzx.datacollector.dao.IUserDataDao;
import com.quartzx.datacollector.model.MonitorData;
import com.quartzx.datacollector.model.RFIDData;
import com.quartzx.datacollector.model.UserData;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zling on 6/29/2016.
 */
@Named
public class MonitorService implements IMonitorService {
    @Inject
    private IRFIDDataDao _rfidDao;
    @Inject
    private IUserDataDao _usrDao;

    public MonitorData analysis(UserData user, int seconds) {
        UserData ud = _usrDao.getUserData(user.getUsername());
        //TODO:
        MonitorData m = new MonitorData();

        if (ud.getPassword().equals(user.getPassword())) {
            List<RFIDData> list = _rfidDao.dataInRange(ud.getDevices(), seconds);
            HashMap<Long, Integer> map = new HashMap<>();
            for (RFIDData data : list) {
                long timestamp = data.getServerTime();
                long trunc = timestamp - timestamp % (60 * 60 * 1000);
                if (map.containsKey(trunc)) {
                    int c = map.get(trunc);
                    map.put(trunc, c + 1);
                } else {
                    map.put(trunc, 1);
                }
            }
            //TODO:
        }
        return m;
    }

    public MonitorData analysis(UserData user) {
        UserData ud = _usrDao.getUserData(user.getUsername());
        //TODO:
        MonitorData m = new MonitorData();

        if (ud.getPassword().equals(user.getPassword())) {
            List<Long> list = _rfidDao.dataInHour(ud.getDevices());
            HashMap<Long, Integer> map = new HashMap<>();
            for (Long timestamp : list) {
                long trunc = timestamp - timestamp % (60 * 1000);
                if (map.containsKey(trunc)) {
                    int c = map.get(trunc);
                    map.put(trunc, c + 1);
                } else {
                    map.put(trunc, 1);
                }
            }

            if (!map.isEmpty()) {
                Long min = Collections.min(map.keySet());
                Long max = Collections.max(map.keySet());
                if (max < min + 10 * 60 * 1000) {
                    max = min + 10 * 60 * 1000;
                }
                for (long x = min; x <= max; x += 60 * 1000) {
                    if (!map.containsKey(x)) {
                        map.put(x, 0);
                    }
                }
            }

            m.setMinuteAggregateMap(map);
        }
        return m;
    }
}
