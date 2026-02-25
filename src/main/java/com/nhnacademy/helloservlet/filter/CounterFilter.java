package com.nhnacademy.helloservlet.filter;

import com.nhnacademy.helloservlet.exercise.CounterUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebFilter (
        filterName = "counterFilter",
        urlPatterns="/*"
)
@Slf4j
public class CounterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CounterUtils.increaseCounter(servletRequest.getServletContext());
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("counter: {}", servletRequest.getServletContext().getAttribute("counter"));
    }
}
