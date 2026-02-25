package com.nhnacademy.helloservlet;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

@HandlesTypes(value = {
        jakarta.servlet.http.HttpServlet.class,
        jakarta.servlet.Filter.class,
        jakarta.servlet.ServletContextListener.class,
        jakarta.servlet.http.HttpSessionListener.class
})
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) {
        servletContext.setInitParameter("counterFileName", "counter.txt");
        servletContext.setInitParameter("url", "https://nhnacademy.com");
    }
}
