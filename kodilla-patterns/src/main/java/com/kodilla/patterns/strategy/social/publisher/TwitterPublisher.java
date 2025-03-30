package com.kodilla.patterns.strategy.social.publisher;

public class TwitterPublisher implements SocialPublisher {
    @Override
    public String share() {
        return "This is a twitter publisher";
    }
}
