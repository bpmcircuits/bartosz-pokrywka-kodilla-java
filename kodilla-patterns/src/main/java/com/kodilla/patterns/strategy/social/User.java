package com.kodilla.patterns.strategy.social;

import com.kodilla.patterns.strategy.social.publisher.SocialPublisher;

public class User {

    private final String name;
    protected SocialPublisher publisher;

    public User(String name, SocialPublisher publisher) {
        this.name = name;
        this.publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public String sharePost() {
        return publisher.share();
    }

    public void setPublisher(SocialPublisher publisher) {
        this.publisher = publisher;
    }
}
