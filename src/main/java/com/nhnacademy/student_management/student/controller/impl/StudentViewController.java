package com.nhnacademy.student_management.student.controller.impl;

import com.nhnacademy.student_management.student.annotation.RequestMapping;
import com.nhnacademy.student_management.student.controller.Command;
import com.nhnacademy.student_management.student.domain.Student;
import com.nhnacademy.student_management.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatter;

@Slf4j
@RequestMapping(value="/student/view.do", method=RequestMapping.Method.GET)
public class StudentViewController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository=(StudentRepository) req.getServletContext()
                .getAttribute("studentRepository");

        String id=req.getParameter("id");

        if(!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("존재하지 않는 학생입니다. id: "+id);
        }

        Student student=studentRepository.getStudentById(id);

        req.setAttribute("student", student);
        req.setAttribute("formattedDate", student.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return "/student/view.jsp";
    }

}