package com.nhnacademy.helloservlet.member.request;

import com.nhnacademy.helloservlet.member.filter.AdminPageFilter;
import com.nhnacademy.helloservlet.member.filter.FilterChain;
import com.nhnacademy.helloservlet.member.filter.MyPageFilter;

public class HttpRequest {
    private final FilterChain filterChain = new FilterChain();

    public HttpRequest() {
        initFilter();
    }

    public void doRequest(Request req) {
        filterChain.doFilter(req);
    }

    private void initFilter() {
        filterChain.addFilter(new MyPageFilter());
        filterChain.addFilter(new AdminPageFilter());
    }
}
