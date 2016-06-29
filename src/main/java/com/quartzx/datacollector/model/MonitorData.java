package com.quartzx.datacollector.model;

import java.util.HashMap;

/**
 * Created by zling on 6/29/2016.
 */
public class MonitorData {
    private HashMap<Long, Integer> _map;

    public void setMinuteAggregateMap(HashMap<Long, Integer> map) {
        _map = map;
    }

    public HashMap<Long, Integer> getMinuteAggregateMap() {
        return _map;
    }
}
