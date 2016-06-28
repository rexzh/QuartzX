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
    @Path("agg")
    @Produces(MediaType.APPLICATION_JSON)
    public Response statictic() {
        return Response.ok().entity(service.analysis()).build();
    }

    @GET
    @Path("ovl")
    @Produces(MediaType.APPLICATION_JSON)
    public Response overall() {
        return Response.ok().entity(service.overall()).build();
    }
}
