package com.quartzx.datacollector;

import org.springframework.core.NestedRuntimeException;

/**
 * Created by zling on 6/21/2016.
 */
public class CommonException extends NestedRuntimeException {

    public CommonException(String msg) {
        super(msg);
    }

    public CommonException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
