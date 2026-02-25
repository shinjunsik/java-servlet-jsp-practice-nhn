package com.nhnacademy.helloservlet.exercise;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

@WebServlet (
        name="multipleServlet",
        urlPatterns = "/multiple"
)
public class MultipleServlet extends HttpServlet {

    private static final Logger log=Logger.getLogger(MultipleServlet.class.getName());

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        String[] values=req.getParameterValues("class");
        String url=getServletContext().getInitParameter("url");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

        try(PrintWriter writer=resp.getWriter()) {
            writer.println("url:"+url);
            writer.println(String.join(", ", values));
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }
}
