package com.quartzx.datacollector.utility;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quartzx.datacollector.CommonException;

import java.io.IOException;

/**
 * Created by zling on 6/21/2016.
 */
public class JsonConverter {
    private JsonConverter() {
    }

    public static String toJsonString(Object obj) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(obj);
        }catch (JsonProcessingException ex){
            throw new CommonException("Json Process error.", ex);
        }
    }

    public static <T> T toObject(String str, Class<T> cls) {
        if (str == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(str, cls);
        } catch (IOException ex) {
            throw new CommonException("Json Parse error.", ex);
        }
    }
}
