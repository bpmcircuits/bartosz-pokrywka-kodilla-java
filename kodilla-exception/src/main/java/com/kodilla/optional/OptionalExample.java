package com.kodilla.optional;

import com.kodilla.exception.nullpointer.User;

import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        User user = new User("user");
        User user1 = null;

        Optional<User> optionalUser = Optional.ofNullable(user);
        Optional<User> optionalUser1 = Optional.ofNullable(user1);

        String username =
                optionalUser1.orElse(new User("gdyUser1JestNull")).getName();

        System.out.println(username);

        optionalUser.ifPresent(u -> System.out.println(u.getName()));
    }
}
