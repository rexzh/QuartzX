package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.IRFIDDataDao;
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
    private IRFIDDataDao _dao;

    public MonitorData analysis(UserData user, int seconds){
        //TODO:
        MonitorData m = new MonitorData();

        List<RFIDData> list = _dao.dataInRange(seconds);
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
        return m;
    }

    public MonitorData analysis(UserData user){
        //TODO:
        MonitorData m = new MonitorData();

        List<Long> list = _dao.dataInHour();
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

        if(!map.isEmpty()) {
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
        return m;
    }
}
