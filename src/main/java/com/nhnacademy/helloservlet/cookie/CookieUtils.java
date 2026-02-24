package com.nhnacademy.helloservlet.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public class CookieUtils {
    private CookieUtils() {
        throw new IllegalStateException("CookieUtils class");
    }

    public static Cookie getCookie(HttpServletRequest req, String name) {
        // req에서 이름이 name인 쿠키를 찾아서 반환 -> 없는 경우 null 반환
        return Optional.ofNullable(req.getCookies())
                // 쿠키 읽어서 배열로 반환
                .flatMap(cookies -> Arrays.stream(cookies)
                        // 쿠키 이름이 name과 일치하는 쿠키 찾기
                        .filter(c -> c.getName().equals(name))
                        // 첫번째 값 반환
                        .findFirst())
                // 없으면 null 반환
                .orElse(null);
    }
}
