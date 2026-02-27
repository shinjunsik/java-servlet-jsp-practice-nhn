package com.nhnacademy.student_management.reflection;

import com.nhnacademy.student_management.reflection.domain.User;
import com.nhnacademy.student_management.reflection.service.InjectUtil;
import com.nhnacademy.student_management.reflection.service.UserService;

public class DiTest {
    public static void main(String[] args) {
        UserService userService= InjectUtil.getObject(UserService.class);

        User user=new User("marco1", 10);

        userService.addUser(user);

        System.out.println(userService.getUser("marco1"));
    }
}
