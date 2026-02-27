package com.nhnacademy.student_management.reflection.repository;

import com.nhnacademy.student_management.reflection.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> userList=new ArrayList<>();

    public User findByName(String userName) {
        return userList.stream()
                .filter(o->o.getUserName().equals(userName))
                .findFirst().orElse(null);
    }

    public void save(User user) {
        this.userList.add(user);
    }
}
