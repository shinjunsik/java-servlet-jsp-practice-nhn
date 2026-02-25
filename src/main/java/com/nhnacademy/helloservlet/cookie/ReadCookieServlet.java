package com.nhnacademy.helloservlet.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@WebServlet (
        name="readCookieServlet",
        urlPatterns = "/read-cookie"
)
public class ReadCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // req에서 locale 쿠키 읽기 -> CookieUtils 사용
        Cookie cookie=CookieUtils.getCookie(req,"locale");

        // 쿠키가 없는 경우 -> 500 에러 발생
        if(Objects.isNull(cookie)) {
            resp.sendError(500, "cookie not found");
            return;
        }

        // 쿠키 value 값 읽기
        String locale=cookie.getValue();

        // message 번들에서 hello 키에 해당하는 값 읽기 -> locale 값으로 언어 설정
        String helloValue= ResourceBundle.getBundle("message", new Locale(locale)).getString("hello");

        // hello 값 응답
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        try(PrintWriter writer=resp.getWriter()) {
            writer.println(helloValue);
        }
    }
}
