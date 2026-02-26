package com.nhnacademy.student_management.controller.impl;

import com.nhnacademy.student_management.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentRegisterFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/student/register.jsp";
    }
}
