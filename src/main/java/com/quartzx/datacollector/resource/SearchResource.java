package com.quartzx.datacollector.resource;

import com.quartzx.datacollector.model.UserData;
import com.quartzx.datacollector.service.ISearchService;
import com.quartzx.datacollector.service.SummaryService;
import com.quartzx.datacollector.utility.BasicAuthentication;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Rex on 2016/8/21.
 */
@Path("search")
public class SearchResource {
    @Inject
    private ISearchService service;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("tag")
    public Response search(@Context HttpHeaders headers, @QueryParam("tagId") String tagId) {
        UserData data = BasicAuthentication.getUserDate(headers);
        return Response.ok().entity(service.searchByTagId(data, tagId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("time")
    public Response search(@Context HttpHeaders headers, @QueryParam("start") long start, @QueryParam("end") long end) {
        UserData data = BasicAuthentication.getUserDate(headers);
        return Response.ok().entity(service.searchByTimeRange(data, start, end)).build();
    }
}
