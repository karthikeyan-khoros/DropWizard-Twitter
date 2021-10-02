package org.example.Resources;

import org.example.Services.AppService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;

@Path("twitter")
@Produces(MediaType.APPLICATION_JSON)
public class AppController {

    AppService appService = new AppService();

    public AppController() {
    }

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

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

    @Path("hometimeline/filter/{word}")
    @GET
    public Response filterHomeTimeLine(@PathParam(value="word") String word) throws IOException
    {
        return  Response.ok().entity(appService.filterHomeTimeLine(word)).build();
    }

}