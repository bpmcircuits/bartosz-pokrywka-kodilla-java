package com.kodilla.testing.forum.statistics;

import java.util.HashMap;
import java.util.Map;

public class ForumStatistics {

    private int userCount;
    private int postsCount;
    private int commentsCount;
    private double averagePostsPerUser;
    private double averageCommentsPerUser;
    private double averageCommentsPerPost;

    public void calculateAdvStatistics(Statistics statistics) {
        this.userCount = statistics.usersNames().size();
        this.postsCount = statistics.postsCount();
        this.commentsCount = statistics.commentsCount();
        averagePostsPerUser = userCount > 0 ? (double) postsCount / userCount : 0;
        averageCommentsPerUser = userCount > 0 ? (double) commentsCount / userCount : 0;
        averageCommentsPerPost = postsCount > 0 ? (double) commentsCount / postsCount : 0;
    }

    public Map<String, Double> showStatistics() {
        return Map.of("Average Post Per User", averagePostsPerUser,
                "Average Comments Per User", averageCommentsPerUser,
                "Average Comments Per Post", averageCommentsPerPost);
    }

    public int getUserCount() {
        return userCount;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public double getAveragePostsPerUser() {
        return averagePostsPerUser;
    }

    public double getAverageCommentsPerUser() {
        return averageCommentsPerUser;
    }

    public double getAverageCommentsPerPost() {
        return averageCommentsPerPost;
    }
}
