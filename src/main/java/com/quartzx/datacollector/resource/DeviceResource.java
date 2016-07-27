package com.quartzx.datacollector.resource;

import com.quartzx.datacollector.model.UserData;
import com.quartzx.datacollector.service.IDeviceService;
import com.quartzx.datacollector.utility.BasicAuthentication;
import com.sun.deploy.net.HttpRequest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by zling on 7/27/2016.
 */
@Path("device")
public class DeviceResource {
    @Inject
    IDeviceService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response validate(@QueryParam("deviceId") String deviceId) {
        return Response.ok().entity(service.validate(deviceId)).build();
    }
}
