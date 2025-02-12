package com.kodilla.stream.forumuser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Forum {

    private final List<ForumUser> forumUserList = new ArrayList<>();

    public Forum(){
        forumUserList.add(new ForumUser(1, "John", 'M',
                LocalDate.of(1997, 1, 3), 20));
        forumUserList.add(new ForumUser(2, "Nick", 'M',
                LocalDate.of(2001, 1, 3), 0));
        forumUserList.add(new ForumUser(3, "Beth", 'F',
                LocalDate.of(2010, 1, 3), 80));
        forumUserList.add(new ForumUser(4, "Julia", 'F',
                LocalDate.of(1993, 1, 3), 8));
        forumUserList.add(new ForumUser(5, "Adam", 'M',
                LocalDate.of(2005, 1, 3), 50));
        forumUserList.add(new ForumUser(6, "Sara", 'F',
                LocalDate.of(1995, 1, 3), 1));
    }

    public List<ForumUser> getUserList() {
        return new ArrayList<>(forumUserList);
    }
}
