package com.quartzx.datacollector.utility;

import com.quartzx.datacollector.CommonException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * Created by zling on 6/27/2016.
 */
public class ResourceManager {
    public String read(String fileName) {
        ClassLoader loader = getClass().getClassLoader();
        try {
            String result = IOUtils.toString(loader.getResourceAsStream(fileName));
            return result;
        } catch (IOException ex){
            throw new CommonException("Read resource fail.", ex);
        }
    }
}
