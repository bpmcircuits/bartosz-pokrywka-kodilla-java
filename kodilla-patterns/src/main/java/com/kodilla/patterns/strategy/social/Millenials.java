package com.kodilla.patterns.strategy.social;

import com.kodilla.patterns.strategy.social.publisher.SocialPublisher;

public class Millenials extends User {
    public Millenials(String name, SocialPublisher publisher) {
        super(name, publisher);
    }
}
