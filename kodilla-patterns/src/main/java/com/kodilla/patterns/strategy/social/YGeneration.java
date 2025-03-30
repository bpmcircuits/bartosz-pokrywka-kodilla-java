package com.kodilla.patterns.strategy.social;

import com.kodilla.patterns.strategy.social.publisher.SocialPublisher;

public class YGeneration extends User {
    public YGeneration(String name, SocialPublisher publisher) {
        super(name, publisher);
    }
}
