package com.nhnacademy.helloservlet.exercise;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.Objects;
import java.util.logging.Logger;

public class ResponseServlet extends HttpServlet {

    private static final Logger log= Logger.getLogger(ResponseServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        log.info("default buffer size: "+ resp.getBufferSize());
        resp.setBufferSize(1024);

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        try(PrintWriter out = resp.getWriter()){

            out.println("locale=" + req.getLocale());
            out.println("parameter name=" + req.getParameter("name"));

            String userId = req.getParameter("userId");

            log.info("userId: " + userId);
            if (userId == null || userId.isEmpty()) {
                resp.reset();

                resp.setStatus(500);
                resp.sendError(500, "name is empty");
                return;
            }

            String redirect = req.getParameter("redirect");
            if(Objects.nonNull(redirect)){
                resp.sendRedirect(redirect);
                return;
            }

            out.println("method=" + req.getMethod());
            out.println("request uri=" + req.getRequestURI());

            resp.resetBuffer();

            out.println("User-Agent header=" + req.getHeader("User-Agent"));

        }catch (Exception e){
            log.warning(e.getMessage());
        }
    }
}
