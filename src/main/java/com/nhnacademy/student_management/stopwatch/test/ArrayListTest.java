package com.nhnacademy.student_management.stopwatch.test;

import com.nhnacademy.student_management.stopwatch.annotation.StopWatch;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest implements PerformanceTestable{

    @StopWatch
    @Override
    public void test() {
        List<Integer> integerList=new ArrayList<>();

        for(int i=0;i<100000000;i++) {
            integerList.add(i);
        }
    }
}
