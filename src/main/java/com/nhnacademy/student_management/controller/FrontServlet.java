package com.nhnacademy.student_management.controller;

import com.nhnacademy.student_management.controller.impl.*;
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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        try {
            Command command=resolveServlet(req.getServletPath(), req.getMethod());
            String view=command.execute(req,resp);


            log.info("View: {}", view);
            if(view.startsWith(REDIRECT_PREFIX)) {
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
            } else {
                req.getRequestDispatcher(view).include(req,resp);
            }
        } catch (Exception e) {
            req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
            req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
            req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
            req.setAttribute("exception",req.getAttribute(ERROR_EXCEPTION));
            req.setAttribute("request_uri",req.getAttribute(ERROR_REQUEST_URI));

            req.getRequestDispatcher("/error.jsp").forward(req,resp);
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
