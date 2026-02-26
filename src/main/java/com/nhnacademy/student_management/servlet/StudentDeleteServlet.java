package com.nhnacademy.student_management.servlet;

import com.nhnacademy.student_management.repository.StudentRepository;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(
        name="studentDeleteServlet",
        urlPatterns = "/student/delete"
)
public class StudentDeleteServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) {
        studentRepository=(StudentRepository) config.
                getServletContext().getAttribute("studentRepository");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");

        if(id==null || !studentRepository.existsById(id)) {
            throw new RuntimeException("id 파라미터가 누락되었거나 해당 학생이 존재하지 않습니다.");
        }

        studentRepository.deleteById(id);

        resp.sendRedirect("/student/list");
    }
}
