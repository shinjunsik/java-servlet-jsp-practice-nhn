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
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
@WebServlet(
        name = "studentViewServlet",
        urlPatterns = "/student/view"
)
public class StudentViewServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository=(StudentRepository) config.
                getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");

        if(Objects.isNull(id)) {
            throw new IllegalArgumentException("id 파라미터는 필수입니다.");
        }

        //todo student 조회
        Student student=studentRepository.getStudentById(id);

        if(Objects.isNull(student)) {
            throw new IllegalArgumentException("해당 id의 학생이 존재하지 않습니다.");
        }

        req.setAttribute("student",student);

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        req.setAttribute("formattedDate", student.getCreatedAt().format(formatter));

        //todo /student/view.jsp <-- forward

        req.getRequestDispatcher("/student/view.jsp").forward(req,resp);

    }

}