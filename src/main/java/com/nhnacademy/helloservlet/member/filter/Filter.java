package com.nhnacademy.helloservlet.member.filter;

import com.nhnacademy.helloservlet.member.request.Request;

public interface Filter {
    void doFilter(Request req, FilterChain filterChain);
}
