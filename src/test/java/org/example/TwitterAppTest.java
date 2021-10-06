package org.example;

import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.example.Models.Tweet;
import org.example.Services.AppService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class TwitterAppTest
{
    /**
     * Rigorous Test :-)
     */

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TwitterServiceTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }


}
