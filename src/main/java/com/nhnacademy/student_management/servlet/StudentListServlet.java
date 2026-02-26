package com.nhnacademy.student_management.servlet;

import com.nhnacademy.student_management.domain.Student;
import com.nhnacademy.student_management.repository.StudentRepository;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet(
        name="studentListServlet",
        urlPatterns = "/student/list"
)
public class StudentListServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) {
        // ServletContext에서 StudentRepository 객체 가져오기
        studentRepository=(StudentRepository)config.
                getServletContext().getAttribute("studentRepository");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // StudentRepository에서 학생 목록 가져오기
        List<Student> studentList=studentRepository.getStudents();
        // 학생 목록을 request attribute에 저장
        req.setAttribute("studentList", studentList);
        // /student/list.jsp 페이지로 forward
        req.getRequestDispatcher("/student/list.jsp").forward(req,resp);
    }
}
