package com.nhnacademy.helloservlet.exercise;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet(name="beautifyServlet", value="/beautify")
public class BeautifyServlet extends HttpServlet {
    private static final Logger log=Logger.getLogger(BeautifyServlet.class.getName());

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String html=req.getParameter("html");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

        try(PrintWriter writer=resp.getWriter()) {
            writer.println(Jsoup.parse(html));
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
