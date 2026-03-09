package com.nhnacademy.student_management.student.controller.impl;

import com.nhnacademy.student_management.student.controller.annotation.RequestMapping;
import com.nhnacademy.student_management.student.controller.Command;
import com.nhnacademy.student_management.student.domain.Gender;
import com.nhnacademy.student_management.student.domain.Student;
import com.nhnacademy.student_management.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@RequestMapping(value="/student/register.do", method=RequestMapping.Method.POST)
public class StudentRegisterController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository=(StudentRepository) req.getServletContext()
                .getAttribute("studentRepository");

        String id=req.getParameter("id");
        String name=req.getParameter("name");
        Gender gender= Gender.valueOf(req.getParameter("gender"));
        int age=Integer.parseInt(req.getParameter("age"));

        Student student = new Student(id,name,gender,age,LocalDateTime.now());

        studentRepository.save(student);

        return "redirect:/student/view.do?id="+id;
    }
}
