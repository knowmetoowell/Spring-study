package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
    // select m from member m where m.nama = ?
    // 이런식으로 자동으로 짜줌


    //Optional<Member> findByNameAndId(String name, Long id);
    // findAll, findById,count, delete 등은 이미 정의 되어 있음(공통 함수)
    // findBy땡땡And땡땡Or땡땡 등으로 함수이름 적으면 jpql이 알아서 쿼리문 짜줌
}
