package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.IRFIDDataDao;
import com.quartzx.datacollector.dao.IUserDataDao;
import com.quartzx.datacollector.model.MonitorData;
import com.quartzx.datacollector.model.RFIDData;
import com.quartzx.datacollector.model.UserData;
import com.quartzx.datacollector.utility.FilterRule;

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

    public MonitorData analysis(UserData user, int seconds, FilterRule rule) {
        UserData ud = _usrDao.getUserData(user.getUsername());

        MonitorData m = new MonitorData();
        HashMap<String, HashMap<Long, Integer>> map = new HashMap<>();

        if (ud.getPassword().equals(user.getPassword())) {
            List<RFIDData> list = _rfidDao.dataInRange(ud.getDevices(), seconds, rule);
            for(String device : ud.getDevices()){
                map.put(device, new HashMap<>());
            }

            for (RFIDData data : list) {
                HashMap<Long, Integer> agg = map.get(data.getId());
                long timestamp = data.getServerTime();
                long trunc = timestamp - timestamp % (60 * 60 * 1000);
                if (agg.containsKey(trunc)) {
                    int c = agg.get(trunc);
                    agg.put(trunc, c + 1);
                } else {
                    agg.put(trunc, 1);
                }
            }
        }

        m.setAggregateMap(map);
        return m;
    }
}
