package com.nhnacademy.student_management.stopwatch;

import com.nhnacademy.student_management.stopwatch.test.ArrayListTest;
import com.nhnacademy.student_management.stopwatch.test.ArrayListTestProxy;

public class ArrayListTestMain {
    public static void main(String[] args) {
        ArrayListTest arrayListTest=new ArrayListTest();
//        arrayListTest.test();

        ArrayListTestProxy arrayListTestProxy=new ArrayListTestProxy(arrayListTest);
        arrayListTestProxy.test();
    }
}
