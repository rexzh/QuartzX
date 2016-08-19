package com.quartzx.datacollector.service;

import java.util.List;

/**
 * Created by zling on 6/20/2016.
 */
public interface ICollectorService {
    List<String> persistRecords(String data);
}
