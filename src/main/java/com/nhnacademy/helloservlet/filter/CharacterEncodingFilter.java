package com.nhnacademy.helloservlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebFilter(
        filterName = "characterEncodingFilter",
        urlPatterns="/*",
        initParams = {
                @WebInitParam(name="encoding", value="utf-8")
        }
)
@Slf4j
public class CharacterEncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding=filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        log.debug("CharacterEncodingFilter: doFilter");
        servletRequest.setCharacterEncoding(this.encoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
