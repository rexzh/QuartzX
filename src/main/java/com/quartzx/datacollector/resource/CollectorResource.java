package com.quartzx.datacollector.resource;

import com.quartzx.datacollector.service.ICollectorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        List<String> ids = service.persistRecords(data);
        return Response.ok().entity(ids).build();
    }
}
