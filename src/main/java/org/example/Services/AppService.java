package org.example.Services;

import org.example.Configuration.Log;
import org.example.Configuration.TwitterObjectBuilder;
import org.example.Models.Tweet;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppService {

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

    public List<Tweet> getHomeTimeLine() throws IOException {
        Twitter twitter = TwitterObjectBuilder.getInstance();

        int page=1,count=5;

        Paging paging = new Paging(page,count);
        List<Status> statuses;

        try {
            Log.getInstance().info("Homeline Fetched ");

            statuses  = twitter.getHomeTimeline(paging);


        }

        catch(TwitterException e)
        {
            Log.getInstance().warning(e.getErrorMessage());
            return null;
        }

        count = statuses.size();
        List<Tweet> tweets = new ArrayList<Tweet>();

        while(count > 0)
        {
            count--;
            tweets.add(new Tweet(statuses.get(count)));
        }

        return tweets;
    }

    public List<Tweet> filterHomeTimeLine(String word) throws IOException
    {

        Twitter twitter = TwitterObjectBuilder.getInstance();

        int page=1,count=5;
        Paging paging = new Paging(page,count);

        List<Status> statuses;

        try {
            Log.getInstance().info("Filtered Homeline Fetched ");

            statuses  = twitter.getHomeTimeline(paging);
        }

        catch(TwitterException e)
        {
            Log.getInstance().info(e.getErrorMessage());
            return null;
        }

        statuses = statuses.parallelStream()
                .filter(t -> t.getText().indexOf(word)>=0)
                .collect(Collectors.toList());

        List<Tweet> tweets = new ArrayList<Tweet>();;

        for(Status status:statuses)
            tweets.add(new Tweet(status));

        return tweets;
    }
}