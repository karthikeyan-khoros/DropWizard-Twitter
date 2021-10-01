package org.example.service;

import org.example.Controller.TwitterObjectBuilder;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppService {

    public String postTweet(String message) throws IOException {
        Twitter twitter = TwitterObjectBuilder.getInstance();

        try {
            Status status = twitter.updateStatus(message);
            System.out.println("Successfully updated the status ----> [" + status.getText() + "].");
        }

        catch (TwitterException e)
        {
            System.out.println("^^^^^^^^^^^^^^^ Operation Failed ^^^^^^^^^^^^^^^\n");
            if(e.getErrorCode()==-1)
                System.out.println("Network Connectivity Error");
            else
                System.out.println(e.getErrorMessage());

            return "Failure";
        }

        return "Successfully updated the status ----> [" + message + "].";
    }

    public List<String> getHomeTimeLine() throws IOException {
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
        List<String> str = new ArrayList<String>();
        while(count > 0)
        {
            count--;
            System.out.println("Tweet "+count+" ---> "+statuses.get(count).getText());
            str.add(statuses.get(count).getText());
        }

        return str;

    }
}