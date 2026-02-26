package com.nhnacademy.student_management.controller.impl;

import com.nhnacademy.student_management.controller.Command;
import com.nhnacademy.student_management.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class StudentDeleteController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository=(StudentRepository) req.getServletContext()
                .getAttribute("studentRepository");

        String id=req.getParameter("id");
        studentRepository.deleteById(id);

        return "redirect:/student/list.do";
    }
}
