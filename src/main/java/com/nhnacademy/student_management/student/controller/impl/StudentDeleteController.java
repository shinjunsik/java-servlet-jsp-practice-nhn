package com.nhnacademy.student_management.student.controller.impl;

import com.nhnacademy.student_management.student.controller.annotation.RequestMapping;
import com.nhnacademy.student_management.student.controller.Command;
import com.nhnacademy.student_management.student.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping(value="/student/delete.do", method=RequestMapping.Method.POST)
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
