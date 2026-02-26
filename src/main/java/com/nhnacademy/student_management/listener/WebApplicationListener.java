package com.nhnacademy.student_management.listener;

import com.nhnacademy.student_management.domain.Gender;
import com.nhnacademy.student_management.domain.Student;
import com.nhnacademy.student_management.repository.JsonStudentRepository;
import com.nhnacademy.student_management.repository.MapStudentRepository;
import com.nhnacademy.student_management.repository.StudentRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@WebListener
public class WebApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context=sce.getServletContext();
//        StudentRepository studentRepository=new MapStudentRepository();
        StudentRepository studentRepository=new JsonStudentRepository();

        Random random=new Random();

        // 학생 10명 생성
        for(int i=1;i<=10;i++) {
            // 성별 랜덤 생성
            Gender gender;
            if(random.nextInt()%2==0) {
                gender=Gender.M;
            } else {
                gender=Gender.F;
            }

            // 학생 객체 생성
            Student student=new Student("student"+i, "아카데미"+i, gender,
                    random.nextInt(10)+20, LocalDateTime.now());

            // 학생 객체 저장
            studentRepository.save(student);
        }
        // ServletContext에 StudentRepository 객체 저장
        context.setAttribute("studentRepository", studentRepository);
        log.info("ServletContext에 StudentRepository 저장 완료 - Student Count: {}", studentRepository.getStudents().size());
    }
}
