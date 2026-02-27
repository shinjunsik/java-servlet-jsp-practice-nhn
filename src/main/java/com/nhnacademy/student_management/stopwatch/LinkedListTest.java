package com.nhnacademy.student_management.stopwatch;

import com.nhnacademy.student_management.stopwatch.test.ArrayListTest;
import com.nhnacademy.student_management.stopwatch.test.ArrayListTestProxy;

public class LinkedListTest {
    public static void main(String[] args) {
        ArrayListTest arrayListTest=new ArrayListTest();

        // ArrayListTest 실행 시간
        long start=System.currentTimeMillis();
        arrayListTest.test();
        long end=System.currentTimeMillis();
        System.out.println("ArrayListTest 실행 시간: "+(end-start)/1000+"seconds");

        // ArrayListTestProxy 실행 시간
        System.out.println("\nArrayListTestProxy 실행");
        ArrayListTestProxy arrayListTestProxy=new ArrayListTestProxy(arrayListTest);
        arrayListTestProxy.test();
    }
}
