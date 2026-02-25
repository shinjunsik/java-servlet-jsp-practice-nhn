package com.nhnacademy.helloservlet.exercise;

import jakarta.servlet.ServletContext;

import java.util.Optional;

public final class CounterUtils {

    private CounterUtils() {
        throw new IllegalStateException("CounterUtility class");
    }

    public static void increaseCounter(ServletContext context) {
        long counter= Optional.ofNullable((Long)context.getAttribute("counter"))
                .orElse(0L);

        counter=counter+1;

        context.setAttribute("counter", counter);
    }
}
