package org.example.Services;

import org.example.Configuration.TwitterObjectBuilder;
import org.example.Models.Tweet;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppService {

    public Tweet postTweet(String message) throws IOException {
        Twitter twitter = TwitterObjectBuilder.getInstance();
        Tweet tweet;

        try {
            Status status = twitter.updateStatus(message);
            tweet = new Tweet(status);
            System.out.println("Successfully updated the status ----> [" + status.getText() + "].");
        }

        catch (TwitterException e)
        {
            System.out.println("^^^^^^^^^^^^^^^ Operation Failed ^^^^^^^^^^^^^^^\n");
            if(e.getErrorCode()==-1)
                System.out.println("Network Connectivity Error");
            else
                System.out.println(e.getErrorMessage());

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
            statuses  = twitter.getHomeTimeline(paging);
        }

        catch(TwitterException e)
        {
            System.out.println("^^^^^^^^^^^^^^^ Operation Failed ^^^^^^^^^^^^^^\n^");
            if(e.getErrorCode()==-1)
                System.out.println("Network Connectivity Error");
            else
                System.out.println(e.getErrorMessage());
            return null;
        }

        count = statuses.size();
        List<Tweet> tweets = new ArrayList<Tweet>();
        while(count > 0)
        {
            count--;
            //System.out.println("Tweet "+count+" ---> "+statuses.get(count).getText());
            tweets.add(new Tweet(statuses.get(count)));
        }

        return tweets;

    }
}