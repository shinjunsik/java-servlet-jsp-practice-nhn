package com.nhnacademy.student_management.controller.impl;

import com.nhnacademy.student_management.controller.Command;
import com.nhnacademy.student_management.domain.Student;
import com.nhnacademy.student_management.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentUpdateFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository=(StudentRepository) req.getServletContext()
                .getAttribute("studentRepository");

        String id=req.getParameter("id");
        Student student=studentRepository.getStudentById(id);
        req.setAttribute("student", student);

        return "/student/register.jsp";
    }
}
