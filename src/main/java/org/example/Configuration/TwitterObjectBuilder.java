package org.example.Configuration;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.Map;

public class TwitterObjectBuilder {

    private static Twitter instance = null;

    private TwitterObjectBuilder() throws IOException {
//        PropertyReader reader = new PropertyReader();
//        Properties prop = reader.getPropValues();

        ConfigurationReader reader = new ConfigurationReader();
        Map<String,Object> data  = reader.getConfiguration();

        Log.getInstance().info("Creating Twitter Object ");

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(data.get("consumerKey").toString())
                .setOAuthConsumerSecret(data.get("consumerSecret").toString())
                .setOAuthAccessToken(data.get("accessToken").toString())
                .setOAuthAccessTokenSecret(data.get("accessTokenSecret").toString());

        TwitterFactory tf = new TwitterFactory(cb.build());
        instance = tf.getInstance();
    }

    public static Twitter getInstance() throws IOException {

        if(instance == null)
        {
            new TwitterObjectBuilder();
        }
        return instance;
    }
}