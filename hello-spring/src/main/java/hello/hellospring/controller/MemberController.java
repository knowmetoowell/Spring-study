package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //컨트롤러 애노테이션이 있으면 스프링 컨테이너가 생성될 때 이 객체를 넣어줌
public class MemberController {

    //private final MemberService memberService = new MemberService();
    // 멤버 컨트롤러는 멤버 서비스의 기능들을 써야함 -> 의존관계가 있다.
    // 근데 new 키워드로 생성하면 새로운 객체가 생성됨
    // 예를 들어 주문 컨트롤러에서도 멤버 서비스를 쓸수 있는데 new 로 생성을 하면 공유가 안됨...ㅠ

    private final MemberService memberService;

    @Autowired // 멤버 서비스를 스프링이 스프링 컨테이너에서 가져와서 연결시켜줌 (의존관계 주입!! dependency injection)
    public MemberController(MemberService memberService) { // MemberService 는 스프링 컨테이너에 자동으로 등록되지 않아서 오류 뜸
        // MemberService 클래스에 @Service 애노테이션 해줘야댐  // 참고 - 스프링컨테이너에 등록 -> 스프링 빈
        this.memberService = memberService;
    }

    @GetMapping("members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
