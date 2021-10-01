package org.example.Controller;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.Properties;

public class TwitterObjectBuilder {

    private static Twitter instance = null;

    private TwitterObjectBuilder() throws IOException {
        PropertyReader reader = new PropertyReader();
        Properties prop = reader.getPropValues();

        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(prop.getProperty("consumerKey"))
                .setOAuthConsumerSecret(prop.getProperty("consumerSecret"))
                .setOAuthAccessToken(prop.getProperty("accessToken"))
                .setOAuthAccessTokenSecret(prop.getProperty("accessTokenSecret"));

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