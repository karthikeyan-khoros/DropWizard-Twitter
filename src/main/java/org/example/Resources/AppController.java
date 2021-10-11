package org.example.Resources;

import org.example.Configuration.CacheConfiguration;
import org.example.Configuration.Log;
import org.example.Models.Tweet;
import org.example.Services.AppService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.TwitterException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Path("twitter")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class AppController {


    @Autowired
    AppService appService;

    Logger logger;

    {
        try {
            logger = Log.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AppController() {
    }

    CacheConfiguration cacheConfiguration = new CacheConfiguration();

    @Autowired
    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Path("newtweet")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postTweet(Map<String,String> data) throws IOException {
        logger.info("Requested Post Tweet ");
        return  Response.ok().entity(appService.postTweet(data.get("tweet"))).build();
    }

    @Path("hometimeline")
    @GET
    public Response getHomeTimeLine() throws Exception
    {
        logger.info("Requested Home Time Line ");

        List<Tweet> homeTimeLine = cacheConfiguration.obtainHomeTimeLineFromCache("hometimeline");
        return Response.ok().entity(homeTimeLine).build();
    }

    @Path("hometimeline/filter/{word}")
    @GET
    public Response filterHomeTimeLine(@PathParam(value="word") String word) throws IOException, TwitterException {
        logger.info("Requested Filtered Home Time-Line ");
        return  Response.ok().entity(appService.filterHomeTimeLine(word)).build();
    }

}