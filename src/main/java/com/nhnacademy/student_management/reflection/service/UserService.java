package com.nhnacademy.student_management.reflection.service;

import com.nhnacademy.student_management.reflection.domain.User;
import com.nhnacademy.student_management.reflection.annotation.Autowired;
import com.nhnacademy.student_management.reflection.repository.UserRepository;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String userName) {
        return userRepository.findByName(userName);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}
