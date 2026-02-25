package com.nhnacademy.helloservlet.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet (
        name="setCookieServlet",
        urlPatterns = "/set-cookie"
)
public class SetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // locale 파라미터 읽기 -> /set-cookie 뒤에 오는 ?locale=en or ?locale=ko로 값 전달
        String locale=req.getParameter("locale");

        // locale 기본값 -> ko
        if(Objects.isNull(locale)) {
            locale="ko";
        }

        // 쿠키 생성 -> name: locale, value: locale (변수 locale의 값)
        Cookie cookie=new Cookie("locale", locale);
        // MaxAge -> -1 : 브라우저 종료시까지 유지 / 0 : 즉시 삭제 / 양수 : 초 단위로 유지 시간 설정
        cookie.setMaxAge(-1);
        // 모든 path에서 cookie 전송 가능 (기본값은 현재 요청의 path)
        cookie.setPath("/");
        // 서블릿 리스폰스에 쿠키 추가
        resp.addCookie(cookie);

        try(PrintWriter writer=resp.getWriter()) {
            writer.println("OK");
        }
    }
}
