package com.kodilla.patterns.strategy.social.publisher;

public class SnapchatPublisher implements SocialPublisher {
    @Override
    public String share() {
        return "This is a snapchat publisher";
    }
}
