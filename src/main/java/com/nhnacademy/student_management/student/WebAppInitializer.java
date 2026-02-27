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

@Slf4j
@HandlesTypes(
        value={
                com.nhnacademy.student_management.student.controller.Command.class
        }
)
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        if(Objects.isNull(c)) {
            log.error("No Command classes found.");
            return;
        }

        ControllerFactory controllerFactory=new ControllerFactory();
        controllerFactory.init(c);
        ctx.setAttribute("controllerFactory", controllerFactory);
    }
}
