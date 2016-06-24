package com.quartzx.datacollector.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by zling on 6/14/2016.
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
public class Startup implements WebApplicationInitializer {
    private static final Logger logger = LoggerFactory.getLogger(Startup.class);
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.debug("========================Start up entry");
        logger.debug("========================Start up complete");
    }
}
