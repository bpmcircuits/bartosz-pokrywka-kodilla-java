package com.kodilla.stream;

import com.kodilla.stream.forumuser.Forum;
import com.kodilla.stream.forumuser.ForumUser;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamMain {
    public static void main(String[] args) {
        Forum forum = new Forum();
        Map<Integer, ForumUser> resultOfForumUsersById = forum.getUserList().stream()
                .filter(forumUser -> forumUser.getSex() != 'F')
                .filter(forumUser ->
                        Period.between(forumUser.getBirthday(), LocalDate.now()).getYears() >= 20)
                .filter(forumUser -> forumUser.getPostsCount() > 1)
                .collect(Collectors.toMap(ForumUser::getUserId, forumUser -> forumUser));

        resultOfForumUsersById.entrySet()
                .forEach(System.out::println);

    }
}