package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service // 스프링 컨테이너에 등록!! 컴포넌트 스캔으로 등록

@Transactional
public class MemberService {
// 컨트롤 쉬프트 T 하니까 바로 테스트 만들어줌 ㄷㄷ

    private final MemberRepository memberRepository;

    //@Autowired // 멤버 서비스를 스프링이 스프링 컨테이너에서 가져와서 연결시켜줌 (의존관계 주입!! dependency injection)
    public MemberService(MemberRepository memberRepository) { // 여기도 멤버컨트롤러랑 마찬가지로 오류남
        // 스프링 컨테이너에 MemberRepository가 등록 되지 않았기 때문 @Repository 애노테이션 추가해줘!
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 멤버는 안된다고 가정
//        Optional<Member> result = memberRepository.findByName(member.getName()); // 컨트롤 쉬프트 v 하면 옵셔널 = ~~ 로나옴
//        result.ifPresent(m -> { //ifPresent -> null이 아니라 어떤 값이 있으면 실행 / 옵셔널이라서 가능함 옵셔널 아니면 if result is not null  쓸듯
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
// 컨트롤 알트 쉬프트 T 누르고 Extract Method 하면 드래그 된것으로 함수 만들어줌 ㄷㄷ
        validateDuplicateMember(member);//중복회원검증

        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // 컨트롤 쉬프트 v 하면 옵셔널 = ~~ 로나옴
            .ifPresent(m -> { //ifPresent -> null이 아니라 어떤 값이 있으면 실행 / findbyname 반환값이 옵셔널이니까 바로 ifpresent
                throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체회원조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 하나 찾기
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
