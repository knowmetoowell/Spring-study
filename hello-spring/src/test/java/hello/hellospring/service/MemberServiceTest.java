package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 실행되기 전에 시작하는 함수
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // 같은 멤버 리포짓토리를 사용하기 위해서 요래해줌... 멤버서비스에서 콘스트럭터 넣음...
        //강의 한번 더보자!! 이게 바로 디펜던시 인젝션~~!!!
    }

    @AfterEach // 각 테스트 후에 실행 되는 함수
    public void afterEach() {
        memberRepository.clearStore(); // 해당 함수가 없으면 각각 테스트가 서로 간섭할 수 있음!!(store에 객체가 여러번 들어가게 되니까)
    }

    @Test
    void 회원가입() {// join 함수 테스트긴 한데 테스트는 그냥 한글로 적어도 무방
        // given(주어진 데이터로) when(어떤 검증을 할때 ) then(이렇게 나와야지~) 으로 테스트하는 방법론

        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());


    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        }
//        catch (IllegalStateException e){
//
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}