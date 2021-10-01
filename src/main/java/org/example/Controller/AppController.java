package org.example.Controller;

import org.example.service.AppService;
import twitter4j.Status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Path("twitter")
@Produces(MediaType.APPLICATION_JSON)
public class AppController {

    AppService appService = new AppService();

    @Path("newtweet")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postTweet(Map<String,String> data) throws IOException {
        return  Response.ok().entity(appService.postTweet(data.get("tweet"))).build();
    }

    @Path("hometimeline")
    @GET
    public Response getHomeTimeLine() throws IOException
    {
        return  Response.ok().entity(appService.getHomeTimeLine()).build();
    }

}