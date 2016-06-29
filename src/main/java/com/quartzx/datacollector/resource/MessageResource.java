package com.quartzx.datacollector.resource;

import com.quartzx.datacollector.service.IMessageService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by zling on 6/29/2016.
 */
@Path("message")
public class MessageResource {
    @Inject
    IMessageService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search() {
        return Response.ok().entity(service.searchLatest()).build();
    }
}
