package org.example.Configuration;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import org.example.Models.Tweet;
import org.example.Services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class CacheConfiguration {

    LoadingCache<String , List<Tweet>> response = null;

    @Autowired
    AppService appService;

    public CacheConfiguration() {
        initialise();
    }

    public void initialise()
    {
        if(response == null)
        {
            response = CacheBuilder.newBuilder()
                    .expireAfterAccess(1, TimeUnit.MINUTES)
                    .maximumSize(200)
                    .build(new CacheLoader<String,List<Tweet>>(){
                        @Override
                        public List<Tweet> load(String key) throws IOException {
                          return new AppService().getHomeTimeLine();
                        }
        });
        }
    }

    public List<Tweet> obtainHomeTimeLineFromCache(String key) throws Exception {
        try {
            CacheStats cacheStats = response.stats();
            return response.get(key);
        } catch (Exception e) {
            throw new Exception("Error occured" + e.getMessage(), e);
        }
    }
}
