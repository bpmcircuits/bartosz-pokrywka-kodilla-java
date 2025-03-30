package com.kodilla.patterns.strategy.social;

import com.kodilla.patterns.strategy.social.publisher.SocialPublisher;

public class ZGeneration extends User {
    public ZGeneration(String name, SocialPublisher publisher) {
        super(name, publisher);
    }
}
