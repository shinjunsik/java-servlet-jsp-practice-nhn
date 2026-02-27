package com.nhnacademy.student_management.student.controller;

import com.nhnacademy.student_management.student.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ControllerFactory {
    private final ConcurrentMap<String, Object> beanMap=new ConcurrentHashMap<>();

    public void init(Set<Class<?>> c) {
        for(Class<?> clazz:c) {
            if(clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping=clazz.getAnnotation(RequestMapping.class);
                String path=requestMapping.value();
                RequestMapping.Method method=requestMapping.method();
                String key=method+":"+path;

                try {
                    Object controller=clazz.getDeclaredConstructor().newInstance();
                    beanMap.put(key, controller);
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
        String key=method+":"+path;

        return beanMap.get(key);
    }
}
