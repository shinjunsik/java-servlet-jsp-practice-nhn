package com.nhnacademy.helloservlet.exercise;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet (
        name="counterServlet",
        urlPatterns = "/counter",
        initParams = {
                @WebInitParam(name="counter", value="100"),
        }
)
public class CounterServlet extends HttpServlet {

    private static final Logger log=Logger.getLogger(CounterServlet.class.getName());

    private long counter;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        counter= Optional.ofNullable(config.getInitParameter("counter"))
                .map(Long::parseLong)
                .orElse(0L);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        counter++;

        try(PrintWriter writer=resp.getWriter()) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset='utf-8'>");
            writer.println("</head>");

            writer.println("<body>");
            writer.printf("<h1>counter: %d</h1>\n", counter);
            writer.println("</body>");
            writer.println("</html>");
        }catch (IOException e) {
            log.warning(e.getMessage());
        }

    }
}
