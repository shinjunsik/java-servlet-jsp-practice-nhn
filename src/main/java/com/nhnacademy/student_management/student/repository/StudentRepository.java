package com.nhnacademy.student_management.student.repository;

import com.nhnacademy.student_management.student.domain.Student;

import java.util.List;

public interface StudentRepository {
    void save(Student student); // 학생 등록
    void update(Student student);   // 학생 정보 수정
    void deleteById(String id); // 학생 삭제
    Student getStudentById(String id);  // ID로 학생 조회
    List<Student> getStudents();    // 학생 리스트 조회
    boolean existsById(String id);  // ID로 학생 존재 여부 확인
}
