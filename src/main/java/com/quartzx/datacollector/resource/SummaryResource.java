package com.quartzx.datacollector.resource;

import com.quartzx.datacollector.service.SummaryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "summaryresource" path)
 */
@Path("summary")
public class SummaryResource {
    @Inject
    private SummaryService service;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response summary() {
        return Response.ok().entity(service.summary()).build();
    }
}
