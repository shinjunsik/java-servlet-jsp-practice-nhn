package com.nhnacademy.student_management.stopwatch.test;

import com.nhnacademy.student_management.stopwatch.annotation.StopWatch;

import java.lang.reflect.Method;
import java.util.Objects;

public class ArrayListTestProxy implements PerformanceTestable{
    private final PerformanceTestable performanceTestable;

    public ArrayListTestProxy(PerformanceTestable performanceTestable) {
        this.performanceTestable=performanceTestable;
    }

    @Override
    public void test() {
        if(hasStopWatch()) {
            long start=System.currentTimeMillis();
            System.out.println("Start Time: "+start);
            performanceTestable.test();

            long end=System.currentTimeMillis();
            System.out.println("End Time: "+end);

            long result=(end-start)/1000;
            System.out.println("Execution Time: "+result+" seconds");
        }
    }

    private boolean hasStopWatch() {
        for(Method method:performanceTestable.getClass().getDeclaredMethods()) {
            StopWatch stopWatch=method.getAnnotation(StopWatch.class);
            if(Objects.nonNull(stopWatch)) {
                return true;
            }
        }
        return false;
    }
}
