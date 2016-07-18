package com.quartzx.datacollector.resource;

import com.quartzx.datacollector.model.UserData;
import com.quartzx.datacollector.service.IMessageService;
import com.quartzx.datacollector.utility.BasicAuthentication;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
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
    public Response search(@Context HttpHeaders headers) {
        UserData data = BasicAuthentication.getUserDate(headers);
        return Response.ok().entity(service.searchLatest(data)).build();
    }
}
