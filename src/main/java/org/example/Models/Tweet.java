package org.example.Models;

import twitter4j.Status;

import java.util.Date;

public class Tweet {

    String message;
    String userName;
    String twitterHandle;
    Date createdAt;
    String profileImageUrl;

    public Tweet(Status status) {

        this.message = status.getText();
        this.createdAt = status.getCreatedAt();
        this.profileImageUrl = status.getUser().getProfileImageURL();
        this.userName = status.getUser().getScreenName();
        this.twitterHandle = status.getUser().getName();

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @Override
    public String toString()
    {
        return "Tweet :"+this.message+"\n"+"User Name :"+this.twitterHandle+"\nCreated At :"+this.createdAt.toString()+"\n\n";
    }
}