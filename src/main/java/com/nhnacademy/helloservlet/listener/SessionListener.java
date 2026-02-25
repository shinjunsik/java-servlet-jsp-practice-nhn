package com.nhnacademy.helloservlet.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@WebListener
@Slf4j
public class SessionListener implements HttpSessionListener {
    private final AtomicInteger atomicInteger=new AtomicInteger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
        int sessionCount=atomicInteger.incrementAndGet();
        log.info("session created counter: {}", sessionCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
        int sessionCount=atomicInteger.incrementAndGet();
        log.info("session destroyed counter: {}", sessionCount);
    }
}
