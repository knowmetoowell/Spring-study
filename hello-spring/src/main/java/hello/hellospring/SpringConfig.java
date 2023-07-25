package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired //생략가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

//    private DataSource dataSource;
//    @Autowired // application.properties 에 정의된 데이터 소스 자동으로 가져와줌크
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);

    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//
//    }
//    @Bean
//    public MemberRepository memberRepository() {
//        // 객체지향설계의 장점(SOLID) 중 Open-Closed principle
//        // 확장에는 열려있고 수정에는 닫혀있다.
//        // 다형성을 활용, DI를 활용하여 기존 코드는 거의 손대지 않고 인터페이스의 구현클래스만 바꿔 끼움
//        //return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        //return new JpaMemberRepository(em);
//
//
//    }


}
