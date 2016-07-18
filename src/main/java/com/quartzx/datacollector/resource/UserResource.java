package com.quartzx.datacollector.resource;

import com.quartzx.datacollector.service.IUserService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Rex on 2016/7/18.
 */
@Path("user")
public class UserResource {
    @Inject
    IUserService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(String data) {
        return Response.ok().entity(service.authenticate(data)).build();
    }
}
