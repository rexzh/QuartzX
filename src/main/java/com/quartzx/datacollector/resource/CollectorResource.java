package com.quartzx.datacollector.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by zling on 6/20/2016.
 */
@Path("collector")
public class CollectorResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upload(String data) {
        //TODO:
        return Response.ok().entity(data).build();
    }
}
