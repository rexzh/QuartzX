package com.quartzx.datacollector.resource;

import com.quartzx.datacollector.service.ICollectorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by zling on 6/20/2016.
 */
@Path("collector")
public class CollectorResource {
    @Inject
    ICollectorService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upload(String data) {
        String id = service.persistRecord(data);
        return Response.ok().entity(id).build();
    }
}
