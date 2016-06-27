package com.quartzx.datacollector.utility;

import com.quartzx.datacollector.CommonException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zling on 6/27/2016.
 */
public class ResourceManager {
    public String read(String fileName) {
        ClassLoader loader = getClass().getClassLoader();
        InputStream stream = loader.getResourceAsStream(fileName);
        if(stream == null)
            throw new CommonException(String.format("%s not found." , fileName));

        try {
            return IOUtils.toString(stream);
        } catch (IOException ex){
            throw new CommonException("Read resource fail.", ex);
        }
    }
}
