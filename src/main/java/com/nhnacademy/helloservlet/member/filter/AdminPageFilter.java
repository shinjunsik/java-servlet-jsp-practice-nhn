package com.nhnacademy.helloservlet.member.filter;

import com.nhnacademy.helloservlet.member.domain.Member;
import com.nhnacademy.helloservlet.member.request.Request;
import com.nhnacademy.helloservlet.member.domain.Role;

import java.util.Objects;

public class AdminPageFilter implements Filter {
    @Override
    public void doFilter(Request req, FilterChain filterChain) {
        if(req.getPath().equals("/admin")) {
            Member member= (Member) req.get("member");

            if(Objects.nonNull(member)) {
            if(member.hasRole(Role.ADMIN)) {
                System.out.println(req.getPath()+"has ADMIN");
                filterChain.doFilter(req);
            } else {
                System.out.println(req.getPath()+"has not ADMIN");
            }
            }
        } else {
            System.out.println("Admin Filter: Next Filter");
            filterChain.doFilter(req);
        }
    }
}
