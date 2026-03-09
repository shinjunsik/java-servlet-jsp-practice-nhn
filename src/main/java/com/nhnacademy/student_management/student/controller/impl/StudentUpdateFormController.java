package com.nhnacademy.student_management.student.controller.impl;

import com.nhnacademy.student_management.student.controller.annotation.RequestMapping;
import com.nhnacademy.student_management.student.controller.Command;
import com.nhnacademy.student_management.student.domain.Student;
import com.nhnacademy.student_management.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(value="/student/update.do", method=RequestMapping.Method.GET)
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
