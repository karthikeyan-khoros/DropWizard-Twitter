package org.example;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.TestSubject;
import org.example.Models.Tweet;
import org.example.Services.AppService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(EasyMockRunner.class)
public class TwitterServiceTest {

    @TestSubject
    static AppService appService = new AppService();

    @Test
    public void testPostTweet() throws IOException {

        Tweet tweetMock = EasyMock.createMock(Tweet.class);
        Tweet emptyTweetMock = EasyMock.createMock(Tweet.class);


        String sampleTweet ="Making a new Tweet"+((new Random()).nextInt());
        String emptyTweet ="";


        EasyMock.expect(tweetMock.getMessage()).andReturn(sampleTweet);
        EasyMock.expect(emptyTweetMock.getMessage()).andReturn(null);

        EasyMock.replay(tweetMock);
        EasyMock.replay(emptyTweetMock);

        Tweet testResult1 = appService.postTweet(tweetMock.getMessage());
        Tweet testResult2 = appService.postTweet(emptyTweetMock.getMessage());

        Assert.assertEquals(sampleTweet,testResult1.getMessage());
        Assert.assertNull(testResult2);
    }

    @Test
    public void testTimeLine() throws IOException
    {

        Tweet tweetMock = EasyMock.createMock(Tweet.class);

        EasyMock.expect(tweetMock.getMessage()).andReturn("A sample Tweet");
        EasyMock.replay(tweetMock);
        List<Tweet> list = new ArrayList<Tweet>();
        list.add(tweetMock);


        List<Tweet> testResult = appService.getHomeTimeLine();
        Assert.assertNotNull(testResult);

    }







}
