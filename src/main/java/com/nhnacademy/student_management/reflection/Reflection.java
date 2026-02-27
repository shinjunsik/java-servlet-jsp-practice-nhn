package com.nhnacademy.student_management.reflection;

import com.nhnacademy.student_management.reflection.domain.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

// Reflection을 이용한 객체 생성
public class Reflection {
    public static void main(String[] args) {
        try {
            // 리플랙션을 이용한 객체 생성
//            Class userClass = Class.forName(User.class.getName());  // 물리적인 클래스 파일명을 인자로 넘겨주면 -> 해당하는 Class를 반환
//            Constructor<?> constructor = userClass.getConstructor();    // public 접근자를 가진 생성자를 반환
//            User user=(User) constructor.newInstance();

            // Arguments를 이용한 객체 생성
//            Constructor cUser= Class.forName(User.class.getName()).getConstructor(String.class, Integer.TYPE);
//            User user = (User) cUser.newInstance("marco", 20);
//
//            System.out.println(user);

            // 리플랙션을 이용한 메서드 호출
//            Class clazz=Class.forName(User.class.getName());
//            Object user=clazz.getDeclaredConstructor().newInstance();
//
//            Method setUserNameMethod=clazz.getDeclaredMethod("setUserName", String.class);
//            setUserNameMethod.invoke(user,"NHN 아카데미");
//
//            Method getUserNameMethod=clazz.getDeclaredMethod("getUserName");
//            String userName=(String) getUserNameMethod.invoke(user);
//
//            Method setUserAgeMethod=clazz.getDeclaredMethod("setUserAge", Integer.TYPE);
//            setUserAgeMethod.invoke(user, 30);
//
//            Method getUserAgeMethod=clazz.getDeclaredMethod("getUserAge");
//            int userAge=(int)getUserAgeMethod.invoke(user);
//
//            System.out.println("userName: "+userName);
//            System.out.println("userAge: "+userAge);

            // 리플랙션 api를 이용한 필드 접근
            Class clazz = Class.forName(User.class.getName());
            Object user =clazz.getDeclaredConstructor().newInstance();

            Field userNameField =clazz.getDeclaredField("userName");
            userNameField.setAccessible(true);
            userNameField.set(user, "marco");

            String userName = (String) userNameField.get(user);

            Field userAgeField =clazz.getDeclaredField("userAge");
            userAgeField.setAccessible(true);
            userAgeField.set(user, 30);

            int userAge = userAgeField.getInt(user);

            System.out.println("userName:" + userName);
            System.out.println("userAge:" + userAge);

        } catch(ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
