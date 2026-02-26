package com.nhnacademy.student_management.controller.impl;

import com.nhnacademy.student_management.controller.Command;
import com.nhnacademy.student_management.domain.Student;
import com.nhnacademy.student_management.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class StudentListController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository=(StudentRepository) req.getServletContext()
                .getAttribute("studentRepository");

        List<Student> studentList=studentRepository.getStudents();
        req.setAttribute("studentList", studentList);

        return "/student/list.jsp";
    }
}
