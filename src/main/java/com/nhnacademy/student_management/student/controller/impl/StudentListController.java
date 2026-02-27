package com.nhnacademy.student_management.student.controller.impl;

import com.nhnacademy.student_management.student.annotation.RequestMapping;
import com.nhnacademy.student_management.student.controller.Command;
import com.nhnacademy.student_management.student.domain.Student;
import com.nhnacademy.student_management.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequestMapping(value="/student/list.do", method=RequestMapping.Method.GET)
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
