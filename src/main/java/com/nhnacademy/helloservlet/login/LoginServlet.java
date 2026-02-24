package com.nhnacademy.helloservlet.login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
public class LoginServlet extends HttpServlet {
    private String initParamId;
    private String initParamPwd;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // init-param에서 id, pwd 읽어서 멤버 변수에 저장 -> web.xml에서 설정한 init-param 값
        initParamId=config.getInitParameter("id");
        initParamPwd=config.getInitParameter("pwd");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 저장된 Session 객체 가져 오기
        HttpSession session=req.getSession(false);

        // Session 객체가 없거나 id값이 없다면 로그인이 안됐다고 판단 -> /login.html 페이지로 Redirect
        if(Objects.isNull(session) || Objects.isNull(session.getAttribute("id"))) {
            resp.sendRedirect("/login.html");
        } else {
            // id 값이 있다면 로그인 성공 판단 -> 로그인 성공 페이지 응답
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");

            try(PrintWriter writer=resp.getWriter()) {
                writer.println("<!DOCTYPE html>");
                writer.println("<html>");
                writer.println("<head>");
                writer.println("<meta charset='utf-8'>");
                writer.println("</head>");

                writer.println("<body>");
                writer.println("login success: id = "+session.getAttribute("id")+"<br/>");
                writer.println("<a href='/logout'>logout</a>");
                writer.println("</body>");
                writer.println("</html>");
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 로그인 폼에서 입력된 id, pwd 읽기
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        // 로그인 폼에서 읽은 id, pwd와 init-param에서 읽은 id, pwd 비교
        // 같다면 Session 객체에 id 저장 & /login 페이지로, 다르다면 /login.html 페이지로 Redirect
        if(initParamId.equals(id) && initParamPwd.equals(pwd)) {
            HttpSession session=req.getSession();
            session.setAttribute("id", id);
            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("/login.html");
        }
    }
}
