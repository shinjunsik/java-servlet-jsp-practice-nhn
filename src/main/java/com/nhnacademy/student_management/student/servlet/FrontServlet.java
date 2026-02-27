package com.nhnacademy.student_management.student.servlet;

import com.nhnacademy.student_management.student.controller.Command;
import com.nhnacademy.student_management.student.controller.ControllerFactory;
import com.nhnacademy.student_management.student.controller.impl.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static jakarta.servlet.RequestDispatcher.*;
import static jakarta.servlet.RequestDispatcher.ERROR_EXCEPTION;
import static jakarta.servlet.RequestDispatcher.ERROR_REQUEST_URI;

@Slf4j
@WebServlet(
        name="frontServlet",
        urlPatterns = "*.do"
)
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect:";
    private ControllerFactory controllerFactory;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controllerFactory=(ControllerFactory) req.getServletContext().getAttribute("controllerFactory");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        try {
            if("/error.do".equals(req.getServletPath()) && req.getAttribute(ERROR_STATUS_CODE) != null) {
                // 이미 에러 처리 중이면 ErrorController 실행
                Command command=(Command) controllerFactory.getBean(req.getMethod(), req.getServletPath());
                if(command != null) {
                    String view = command.execute(req, resp);
                    req.getRequestDispatcher(view).include(req, resp);
                }
                return;
            }
//            Command command=resolveServlet(req.getServletPath(), req.getMethod());
            Command command=(Command) controllerFactory.getBean(req.getMethod(), req.getServletPath());

            if(command==null) {
                throw new IllegalArgumentException("No command found for url ");
            }

            String view=command.execute(req,resp);


            log.info("View: {}", view);
            if(view.startsWith(REDIRECT_PREFIX)) {
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
            } else {
                req.getRequestDispatcher(view).include(req,resp);
            }
        } catch (Exception e) {
            req.setAttribute(ERROR_STATUS_CODE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            req.setAttribute(ERROR_EXCEPTION_TYPE, e.getClass());
            req.setAttribute(ERROR_MESSAGE, e.getMessage());
            req.setAttribute(ERROR_EXCEPTION, e);
            req.setAttribute(ERROR_REQUEST_URI, req.getRequestURI());

            req.getRequestDispatcher("/error.do").forward(req,resp);
        }
    }

    private Command resolveServlet(String servletPath, String method) {
        Command command=null;
        if("/student/list.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentListController();
        }else if("/student/view.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentViewController();
        }else if("/student/delete.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentDeleteController();
        }else if("/student/update.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentUpdateFormController();
        }else if("/student/update.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentUpdateController();
        }else if("/student/register.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentRegisterFormController();
        }else if("/student/register.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentRegisterController();
        }else if("/error.do".equals(servletPath)){
            command = new ErrorController();
        }
        return command;
    }
}
