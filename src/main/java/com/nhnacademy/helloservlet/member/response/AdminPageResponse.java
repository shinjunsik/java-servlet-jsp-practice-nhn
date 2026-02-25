package com.nhnacademy.helloservlet.member.response;

import com.nhnacademy.helloservlet.member.domain.Member;
import com.nhnacademy.helloservlet.member.domain.Role;
import com.nhnacademy.helloservlet.member.request.Request;

public class AdminPageResponse implements Response{
    @Override
    public void doResponse(Request request) {
        System.out.println("###### response:AdminPageResponse #####");
        Member member = (Member) request.get("member");
        System.out.println("아이디:" + member.getId());
        System.out.println("이름:" + member.getName());
        System.out.println("등급:" + Role.ADMIN);
        System.out.println("이메일: marco@nhnacademy.com");
        System.out.println("do something ... ADMIN ...");
    }
}
