package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //컨트롤러 애노테이션이 클래스 객체를 생성해서 스프링에 넣어두고 관리함
// 스프링 컨테이너에서 스프링 빈이 관리된다고 표현함
public class MemberController {
    private final MemberService memberService;


    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
