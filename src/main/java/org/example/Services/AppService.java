package org.example.Services;

import org.example.Configuration.CacheConfiguration;
import org.example.Configuration.Log;
import org.example.Configuration.TwitterObjectBuilder;
import org.example.Models.Tweet;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppService {

    CacheConfiguration cacheConfiguration = new CacheConfiguration();

    Logger logger;

    {
        try {
            logger = Log.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  static final String key = "hometimeline";

    public Tweet postTweet(String message) throws IOException {

        Twitter twitter = TwitterObjectBuilder.getInstance();
        Tweet tweet;

        try {
            logger.info("New Status Post :"+message);

            Status status = twitter.updateStatus(message);
            tweet = new Tweet(status);

            Log.getInstance().info(" Status Posted ----> [" + status.getText() + "].");
        }

        catch (TwitterException e)
        {
            logger.warn(e.getErrorMessage());
            return null;
        }

        return tweet;
    }




    public List<Tweet> getHomeTimeLine() throws IOException{
        Twitter twitter = TwitterObjectBuilder.getInstance();

        final int page=1,pageCount=10;
        int count,index =0;

        Paging paging = new Paging(page,pageCount);
        List<Status> statuses;
        List<Tweet> tweets = null;

        try {

                logger.info("Fetching Home TimeLine from Twitter");
                statuses  = twitter.getHomeTimeline(paging);

                count = statuses.size();

                tweets = new ArrayList<Tweet>();

                while(count > 0)
                {
                    count--;
                    tweets.add(new Tweet(statuses.get(index++)));
                }

        }

        catch(TwitterException e)
        {
           logger.warn(e.getErrorMessage());
            return null;
        }
        return tweets;
    }

    public List<Tweet> filterHomeTimeLine(String word) throws IOException, TwitterException {

        List<Tweet> tweets = null;

        try {
            logger.info("Filtered HomeTimeline Fetched ");
            tweets = cacheConfiguration.obtainHomeTimeLineFromCache(key);
        }
       catch (Exception e) {
            logger.info(e.getMessage());
        }

        tweets = tweets.parallelStream()
                .filter(t -> t.getMessage().indexOf(word)>=0)
                .collect(Collectors.toList());

        return tweets;
    }
}