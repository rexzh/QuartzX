package com.quartzx.datacollector.resource;

import com.quartzx.datacollector.service.IMonitorService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by zling on 6/29/2016.
 */
@Path("monitor")
public class MonitorResource {
    @Inject
    IMonitorService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response summary() {
        return Response.ok().entity(service.analysis()).build();
    }
}
