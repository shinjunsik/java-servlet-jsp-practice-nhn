package com.nhnacademy.student_management.servlet;

import com.nhnacademy.student_management.domain.Gender;
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
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@WebServlet(
        name="studentUpdateServlet",
        urlPatterns = "/student/update"
)
public class StudentUpdateServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) {
        studentRepository=(StudentRepository) config
                .getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        Student student=studentRepository.getStudentById(id);

        req.setAttribute("student", student);
//        req.getRequestDispatcher("/student/register.jsp").forward(req,resp);
        req.setAttribute("view", "/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id=req.getParameter("id");
        String name=req.getParameter("name");
        Gender gender= Gender.valueOf(req.getParameter("gender"));
        int age=Integer.parseInt(req.getParameter("age"));

        if(Objects.isNull(id) || Objects.isNull(name)) {
            throw new IllegalArgumentException("모든 필드를 입력해주세요.");
        }

        Student student = new Student(id, name, gender, age, LocalDateTime.now());

        studentRepository.update(student);

//        resp.sendRedirect(req.getContextPath()+ "/student/view?id="+id);
        req.setAttribute("view", "redirect:/student/view.do?id="+id);
    }
}
