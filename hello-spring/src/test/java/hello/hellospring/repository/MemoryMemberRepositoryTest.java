package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest { // 테스트 먼저 만들고 비지니스 만들면 그게 바로 테스트주도설계
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각 테스트 후에 실행 되는 함수
    public void afterEach() {
        repository.clearStore(); // 해당 함수가 없으면 각각 테스트가 서로 간섭할 수 있음!!(store에 객체가 여러번 들어가게 되니까)
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // System.out.println("result = " + (member==result)); 테스트에서 이렇게 글자로 다 볼수없음 -> 어설션스
        //Assertions.assertEquals(member, result);

        Assertions.assertThat(member).isEqualTo(member); // assertj
    }

    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result = repository.findByName("spring1").get();
        //then
        Assertions.assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
