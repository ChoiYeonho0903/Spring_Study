package study.data.jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import study.data.jpa.entity.Member;

@Repository
public class MemberJpaRepository {

    @PersistenceContext // spring container 가 entityManager 를 가져다 준다.
    private EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
