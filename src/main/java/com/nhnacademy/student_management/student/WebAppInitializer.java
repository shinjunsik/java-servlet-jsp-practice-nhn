package com.nhnacademy.student_management.student;

import com.nhnacademy.student_management.student.controller.ControllerFactory;
import com.nhnacademy.student_management.student.repository.StudentRepository;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Set;

// Tomcat이 시작될 때 자동으로 호출되는 클래스
// ServletContainerInitializer 인터페이스를 구현 -> 웹 애플리케이션 초기화 작업을 수행
@Slf4j
@HandlesTypes(
        value={
                com.nhnacademy.student_management.student.controller.Command.class
                // Command 인터페이스를 구현한 클래스들을 자동으로 검색하여 Set<Class<?>> c에 전달
        }
)
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        if(Objects.isNull(c)) {
            log.error("No Command classes found.");
            return;
        }

        // StudentRepository 객체를 생성하여 ServletContext에 저장 -> 모든 서블릿에서 공유 가능
        ControllerFactory controllerFactory=new ControllerFactory();
        controllerFactory.init(c);
        ctx.setAttribute("controllerFactory", controllerFactory);
    }
}
