package com.quartzx.datacollector.resource;

import com.quartzx.datacollector.model.UserData;
import com.quartzx.datacollector.service.ISummaryService;
import com.quartzx.datacollector.service.SummaryService;
import com.quartzx.datacollector.utility.BasicAuthentication;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "summaryresource" path)
 */
@Path("summary")
public class SummaryResource {
    @Inject
    private ISummaryService service;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response summary(@Context HttpHeaders headers) {
        UserData data = BasicAuthentication.getUserDate(headers);
        return Response.ok().entity(service.summary(data)).build();
    }
}
