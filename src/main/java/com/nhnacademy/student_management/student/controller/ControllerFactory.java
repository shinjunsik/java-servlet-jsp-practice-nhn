package com.nhnacademy.student_management.student.controller;

import com.nhnacademy.student_management.student.controller.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ControllerFactory {
    private final ConcurrentMap<String, Object> beanMap=new ConcurrentHashMap<>();

    // 클래스 객체 집합을 받아서 RequestMapping이 붙은 클래스들을 찾아서 인스턴스를 생성하여 beanMap에 저장
    public void init(Set<Class<?>> c) {
        for(Class<?> clazz:c) {
            if(clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping=clazz.getAnnotation(RequestMapping.class);
                String path=requestMapping.value();
                RequestMapping.Method method=requestMapping.method();
                String key=method+":"+path; // method+path 형태로 key 생성

                try {
                    Object controller=clazz.getDeclaredConstructor().newInstance();
                    // StopWatchProxy로 감싸서 beanMap에 저장 -> 성능 측정 기능 추가
                    // StopWatchProxy 재사용성 증가 -> aop 프록시 패턴 적용 가능
                    beanMap.put(key, new StopWatchProxy((Command) controller));
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Object getBean(String method,String path) {
        String key=method+":"+path; // method+path 형태로 key 생성

        return beanMap.get(key);    // key에 해당하는 컨트롤러 객체 반환
    }
}
