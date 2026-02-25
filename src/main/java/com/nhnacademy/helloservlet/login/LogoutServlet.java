package com.nhnacademy.helloservlet.login;

import com.nhnacademy.helloservlet.cookie.CookieUtils;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Objects;

@WebServlet (
        name="logoutServlet",
        urlPatterns = "/logout"
)
public class LogoutServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Session 값 읽기 -> 로그인 여부 판단
        HttpSession session =req.getSession(false);

        // Session이 없는 경우 -> 로그인 안 한 상태 -> /login.html 페이지로 Redirect
        if(Objects.isNull(session)) {
            session.invalidate();
        }

        // JSESSIONID 쿠키 읽기
        Cookie cookie= CookieUtils.getCookie(req,"JSESSIONID");

        // 쿠키 값이 존재 하는 경우 -> 쿠키 삭제 (value 빈 문자열, MaxAge 0으로 설정)
        if(Objects.nonNull(cookie)) {
            cookie.setValue("");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }

        // /login.html 페이지로 Redirect
        resp.sendRedirect("/login.html");
    }
}
