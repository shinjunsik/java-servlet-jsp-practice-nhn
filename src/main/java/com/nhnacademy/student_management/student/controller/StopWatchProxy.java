package com.nhnacademy.student_management.student.controller;

import com.nhnacademy.student_management.stopwatch.annotation.StopWatch;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

// Command 인터페이스를 구현하는 프록시 클래스
@Slf4j
public class StopWatchProxy implements Command {
    private Command command;

    public StopWatchProxy(Command command) {
        this.command = command;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        if(command.getClass().isAnnotationPresent(StopWatch.class)) {
            // StopWatch 어노테이션이 붙은 클래스만 실행 시간을 측정하도록 함 (aop의 pointcut과 유사한 기능)
            // 실행 시간 측정을 비즈니스로직과 분리 -> 관심사의 분리 (단일 책임 원칙) -> 관점 지향 프로그래밍 (aop)
            long startTime = System.currentTimeMillis();    // 실행 시작 시간

            String view = command.execute(req, resp);

            long endTime = System.currentTimeMillis();      // 실행 종료 시간

            log.info("실행 시간: " + (endTime - startTime) + "ms");

            return view;
        } else {
            // StopWatch 어노테이션이 없는 클래스는 그냥 실행 -> 실행 시간 측정 X
            return command.execute(req, resp);
        }
    }
}
