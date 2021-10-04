package org.example.Models;

import twitter4j.Status;
import twitter4j.URLEntity;

import java.util.Date;

public class Tweet {

    String message;
    String userName;
    String twitterHandle;
    String createdAt;
    String profileImageUrl;
    String link;

    public Tweet(Status status) {

        this.message = status.getText();
        this.createdAt = status.getCreatedAt().toGMTString();
        this.profileImageUrl = status.getUser().getProfileImageURL();
        this.userName = status.getUser().getScreenName();
        this.twitterHandle = status.getUser().getName();

        this.link = "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId();
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "message='" + message + '\'' +
                ", userName='" + userName + '\'' +
                ", twitterHandle='" + twitterHandle + '\'' +
                ", createdAt=" + createdAt +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

}