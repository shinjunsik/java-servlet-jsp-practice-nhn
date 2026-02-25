package com.nhnacademy.helloservlet.member.filter;

import com.nhnacademy.helloservlet.member.request.Request;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FilterChain {
    private List<Filter> filters = new LinkedList<>();
    private Iterator iterator;

    public void addFilter(Filter filter) {
        this.filters.add(filter);
        iterator=filters.iterator();
    }

    public void doFilter(Request req) {
        if(iterator.hasNext()) {
            Filter nextFilter = (Filter) iterator.next();
            nextFilter.doFilter(req, this);
        } else {
            if(req.getPath().equals("/mypage")) {
            }
        }
    }
}
