package com.quartzx.datacollector.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zling on 5/23/2016.
 */
public class Summarize {
    private long sum;

    public long getSum() {
        return sum;
    }

    public void setSum(long value) {
        sum = value;
    }

    private HashMap<Long, Integer> _map;
    public void setMinuteAggregateMap(HashMap<Long, Integer> map) {
        _map = map;
    }

    public HashMap<Long, Integer> getMinuteAggregateMap(){
        return _map;
    }
}
