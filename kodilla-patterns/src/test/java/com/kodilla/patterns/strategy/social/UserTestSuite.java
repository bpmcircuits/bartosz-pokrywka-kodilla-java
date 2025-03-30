package com.kodilla.patterns.strategy.social;

import com.kodilla.patterns.strategy.social.publisher.FacebookPublisher;
import com.kodilla.patterns.strategy.social.publisher.SnapchatPublisher;
import com.kodilla.patterns.strategy.social.publisher.TwitterPublisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTestSuite {

    @Test
    void testDefaultSharingStrategies() {
        //given
        User userOne = new User("John", new FacebookPublisher());
        User userTwo = new User("Sara", new SnapchatPublisher());
        User userThree = new User("Patric", new TwitterPublisher());
        //when
        String actualOne = userOne.sharePost();
        String actualTwo = userTwo.sharePost();
        String actualThree = userThree.sharePost();
        //then
        assertEquals("This is a facebook publisher", actualOne);
        assertEquals("This is a snapchat publisher", actualTwo);
        assertEquals("This is a twitter publisher", actualThree);
    }

    @Test
    void testIndividualSharingStrategy() {
        //given
        User userOne = new User("John", new FacebookPublisher());
        //when
        String actualBefore = userOne.sharePost();
        userOne.setPublisher(new TwitterPublisher());
        String actualAfter = userOne.sharePost();
        //then
        assertEquals("This is a facebook publisher", actualBefore);
        assertEquals("This is a twitter publisher", actualAfter);
    }
}