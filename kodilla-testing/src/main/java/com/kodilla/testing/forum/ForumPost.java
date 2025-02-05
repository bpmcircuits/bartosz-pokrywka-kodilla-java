package com.kodilla.testing.forum;

import java.util.Objects;

public class ForumPost {

    private String postBody;
    private String author;

    public ForumPost(String postBody, String author) {
        this.postBody = postBody;
        this.author = author;
    }

    public String getPostBody() {
        return postBody;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ForumPost forumPost = (ForumPost) o;
        return postBody.equals(forumPost.postBody) && author.equals(forumPost.author);
    }

    @Override
    public int hashCode() {
        int result = postBody.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }
}
