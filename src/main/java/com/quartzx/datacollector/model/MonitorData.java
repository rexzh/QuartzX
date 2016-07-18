package com.quartzx.datacollector.model;

import java.util.HashMap;

/**
 * Created by zling on 6/29/2016.
 */
public class MonitorData {
    private HashMap<String, HashMap<Long, Integer>> _map;

    public void setAggregateMap(HashMap<String, HashMap<Long, Integer>> map) {
        _map = map;
    }

    public HashMap<String, HashMap<Long, Integer>> getAggregateMap() {
        return _map;
    }
}
