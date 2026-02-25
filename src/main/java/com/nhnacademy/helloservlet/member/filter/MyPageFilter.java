package com.nhnacademy.helloservlet.member.filter;

import com.nhnacademy.helloservlet.member.domain.Member;
import com.nhnacademy.helloservlet.member.request.Request;
import com.nhnacademy.helloservlet.member.domain.Role;

import java.util.Objects;

public class MyPageFilter implements Filter {
    @Override
    public void doFilter(Request req, FilterChain filterChain) {
        if(req.getPath().equals("/mypage")) {
            Member member= (Member) req.get("member");

            if(Objects.nonNull(member)) {
                if(member.hasRole(Role.USER)) {
                    System.out.println(req.getPath()+"has USER");
                    filterChain.doFilter(req);
                } else {
                    System.out.println(req.getPath()+"has not USER");
                }
            }
        } else {
            System.out.println("My Page Filter: Next Filter");
            filterChain.doFilter(req);
        }
    }
}
