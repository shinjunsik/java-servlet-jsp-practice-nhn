package com.nhnacademy.helloservlet.exercise;

import java.io.*;
import java.util.Objects;
import java.util.logging.Logger;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(
        name="helloServlet",
        urlPatterns = "/hello",
        initParams = {
                @WebInitParam(name="title", value="Mr."),
                @WebInitParam(name="name", value="marco"),
        }
)
public class HelloServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(HelloServlet.class.getName());

    public void init(ServletConfig config) throws ServletException {
        log.info("init!");
        super.init(config);
    }

    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("service!");
        super.service(req,resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String title=getServletConfig().getInitParameter("title");
        String name=getServletConfig().getInitParameter("name");

        log.info("title: "+title);
        log.info("name: "+name);

        if(Objects.isNull(title)) {
            title="Mr.";
        }

        if(Objects.isNull(name)) {
            name="marco";
        }

        resp.setCharacterEncoding("utf-8");
        try(PrintWriter writer=resp.getWriter()) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset='utf-8'>");
            writer.println("</head>");

            writer.println("<body>");
            writer.println("<h1>hello servlet!</h1>");
            writer.println("<h1>안녕 서블릿!</h1>");
            writer.printf("<h1>hello %s %s!</h1>\n", title, name);
            writer.println("<h1>counter: " +getServletContext().getAttribute("counter") +"</h1>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }

    public void destroy() {
        log.info("destroy!");
        super.destroy();
    }
}