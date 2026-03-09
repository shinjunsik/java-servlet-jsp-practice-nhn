package com.nhnacademy.student_management.stopwatch.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// ElementType.METHOD: 메서드에 적용
// ElementType.TYPE: 클래스, 인터페이스, 열거형 등에 적용
@Target(value={ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface StopWatch {
}
