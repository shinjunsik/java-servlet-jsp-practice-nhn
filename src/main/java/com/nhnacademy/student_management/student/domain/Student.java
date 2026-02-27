package com.nhnacademy.student_management.student.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter     // getter/setter 자동 생성
@NoArgsConstructor  // 기본 생성자 -> 필드 값이 없는 생성자
@AllArgsConstructor // 모듬 필드 값이 필요한 생성자
public class Student {
    private String id;
    private String name;
    private Gender gender;
    private int age;
    private LocalDateTime createdAt;
}
