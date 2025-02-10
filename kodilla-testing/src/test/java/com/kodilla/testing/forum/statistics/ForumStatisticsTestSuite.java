package com.kodilla.testing.forum.statistics;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@DisplayName("TDD: Forum statistics test suite")
public class ForumStatisticsTestSuite {

    @Mock
    private Statistics statisticsMock;
    
    @Test
    void testWhenPostsCountZero() {
        // Given
        ForumStatistics forumStatistics = new ForumStatistics();
        when(statisticsMock.usersNames()).thenReturn(new ArrayList<>());
        when(statisticsMock.postsCount()).thenReturn(0);
        when(statisticsMock.commentsCount()).thenReturn(0);

        // When
        forumStatistics.calculateAdvStatistics(statisticsMock);

        // Then
        assertEquals(0, forumStatistics.getAveragePostsPerUser());
        assertEquals(0, forumStatistics.getAverageCommentsPerUser());
        assertEquals(0, forumStatistics.getAverageCommentsPerPost());

    }

    @Test
    void testWhenPostsCountOneThousand() {
        // Given
        ForumStatistics forumStatistics = new ForumStatistics();
        when(statisticsMock.usersNames()).thenReturn(userGenerate(500));
        when(statisticsMock.postsCount()).thenReturn(1000);
        when(statisticsMock.commentsCount()).thenReturn(2000);

        // When
        forumStatistics.calculateAdvStatistics(statisticsMock);

        // Then
        assertEquals(2, forumStatistics.getAveragePostsPerUser());
        assertEquals(4, forumStatistics.getAverageCommentsPerUser());
        assertEquals(2, forumStatistics.getAverageCommentsPerPost());
    }

    @Test
    void testWhenCommentsCountZero() {
        // Given
        ForumStatistics forumStatistics = new ForumStatistics();
        when(statisticsMock.usersNames()).thenReturn(userGenerate(100));
        when(statisticsMock.postsCount()).thenReturn(1000);
        when(statisticsMock.commentsCount()).thenReturn(0);

        // When
        forumStatistics.calculateAdvStatistics(statisticsMock);

        // Then
        assertEquals(10, forumStatistics.getAveragePostsPerUser());
        assertEquals(0, forumStatistics.getAverageCommentsPerUser());
        assertEquals(0, forumStatistics.getAverageCommentsPerPost());
    }

    @Test
    void testWhenCommentsCountLessThanPostsCount() {
        // Given
        ForumStatistics forumStatistics = new ForumStatistics();
        when(statisticsMock.usersNames()).thenReturn(userGenerate(100));
        when(statisticsMock.commentsCount()).thenReturn(10);
        when(statisticsMock.postsCount()).thenReturn(5);

        // When
        forumStatistics.calculateAdvStatistics(statisticsMock);

        // Then
        assertEquals(0.05, forumStatistics.getAveragePostsPerUser());
        assertEquals(0.1, forumStatistics.getAverageCommentsPerUser());
        assertEquals(2, forumStatistics.getAverageCommentsPerPost());
    }

    @Test
    void testWhenCommentsCountLargerThanPostsCount() {
        // Given
        ForumStatistics forumStatistics = new ForumStatistics();
        when(statisticsMock.usersNames()).thenReturn(userGenerate(100));
        when(statisticsMock.commentsCount()).thenReturn(8);
        when(statisticsMock.postsCount()).thenReturn(20);

        // When
        forumStatistics.calculateAdvStatistics(statisticsMock);

        // Then
        assertEquals(0.2, forumStatistics.getAveragePostsPerUser());
        assertEquals(0.08, forumStatistics.getAverageCommentsPerUser());
        assertEquals(0.4, forumStatistics.getAverageCommentsPerPost());
    }

    @Test
    void testWhenUserCountEqualsZero() {
        // Given
        ForumStatistics forumStatistics = new ForumStatistics();
        when(statisticsMock.usersNames()).thenReturn(new ArrayList<>());
        when(statisticsMock.commentsCount()).thenReturn(10);
        when(statisticsMock.postsCount()).thenReturn(5);

        // When
        forumStatistics.calculateAdvStatistics(statisticsMock);

        // Then
        assertEquals(0, forumStatistics.getAveragePostsPerUser());
        assertEquals(0, forumStatistics.getAverageCommentsPerUser());
        assertEquals(2, forumStatistics.getAverageCommentsPerPost());

    }

    @Test
    void testWhenUserCountEqualsOneHundred() {
        // Given
        ForumStatistics forumStatistics = new ForumStatistics();
        when(statisticsMock.usersNames()).thenReturn(userGenerate(100));
        when(statisticsMock.commentsCount()).thenReturn(1500);
        when(statisticsMock.postsCount()).thenReturn(20);

        // When
        forumStatistics.calculateAdvStatistics(statisticsMock);

        // Then
        assertEquals(0.2, forumStatistics.getAveragePostsPerUser());
        assertEquals(15, forumStatistics.getAverageCommentsPerUser());
        assertEquals(75, forumStatistics.getAverageCommentsPerPost());

    }

    private List<String> userGenerate(int count) {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            output.add("User" + i);
        }
        return output;
    }

}
