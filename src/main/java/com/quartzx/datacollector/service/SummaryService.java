package com.quartzx.datacollector.service;

import com.quartzx.datacollector.dao.ISummaryDao;
import com.quartzx.datacollector.model.Summarize;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zling on 5/23/2016.
 */
@Named
public class SummaryService implements ISummaryService {
    @Inject
    private ISummaryDao _dao;

    public Summarize analysis() {
        Summarize s = new Summarize();
        s.setSum(_dao.count());

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

        s.setMinuteAggregateMap(map);
        return s;
    }
}
