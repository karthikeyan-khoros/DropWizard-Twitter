package org.example.Services;

import org.example.Configuration.CacheConfiguration;
import org.example.Configuration.Log;
import org.example.Configuration.TwitterObjectBuilder;
import org.example.Models.Tweet;
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

    public  static  String key = "hometimeline";

    public Tweet postTweet(String message) throws IOException {

        Twitter twitter = TwitterObjectBuilder.getInstance();
        Tweet tweet;

        try {
            Log.getInstance().info("New Status Post :"+message);

            Status status = twitter.updateStatus(message);
            tweet = new Tweet(status);

            Log.getInstance().info(" Status Posted ----> [" + status.getText() + "].");
        }

        catch (TwitterException e)
        {
            Log.getInstance().warning(e.getErrorMessage());
            return null;
        }

        return tweet;
    }




    public List<Tweet> getHomeTimeLine() throws IOException{
        Twitter twitter = TwitterObjectBuilder.getInstance();

        int page=1,count=5;

        Paging paging = new Paging(page,count);
        List<Status> statuses;
        List<Tweet> tweets = null;

        try {

                Log.getInstance().info("Fetching Home TimeLine from Twitter");
                statuses  = twitter.getHomeTimeline(paging);


                count = statuses.size();
                tweets = new ArrayList<Tweet>();

                while(count > 0)
                {
                    count--;
                    tweets.add(new Tweet(statuses.get(count)));
                }

        }

        catch(TwitterException e)
        {
            Log.getInstance().warning(e.getErrorMessage());
            return null;
        }

        return tweets;
    }

    public List<Tweet> filterHomeTimeLine(String word) throws IOException, TwitterException {

        int page=1,count=5;
        Paging paging = new Paging(page,count);

        List<Tweet> tweets = null;

        try {
            Log.getInstance().info("Filtered Homeline Fetched ");
            tweets = cacheConfiguration.obtainHomeTimeLineFromCache(key);
        }
        catch(TwitterException e)
        {
            Log.getInstance().info(e.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        tweets = tweets.parallelStream()
                .filter(t -> t.getMessage().indexOf(word)>=0)
                .collect(Collectors.toList());

        return tweets;
    }
}