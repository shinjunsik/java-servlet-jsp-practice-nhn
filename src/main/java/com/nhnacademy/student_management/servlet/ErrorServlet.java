package com.nhnacademy.student_management.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.RequestDispatcher.*;

@WebServlet(
        name="errorServlet",
        urlPatterns = "/error"
)
public class ErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
        req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
        req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
        req.setAttribute("exception",req.getAttribute(ERROR_EXCEPTION));
        req.setAttribute("request_uri",req.getAttribute(ERROR_REQUEST_URI));

        req.getRequestDispatcher("/error.jsp").forward(req,resp);
    }
}
