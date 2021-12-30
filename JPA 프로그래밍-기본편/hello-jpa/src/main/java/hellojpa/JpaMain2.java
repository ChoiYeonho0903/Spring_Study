package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            //비영속
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("MemberA");

//            //영속
//            System.out.println("=== BEFORE ===");
//            em.persist(member);
//            System.out.println("=== AFTER ===");

//            //준영속
//            em.detach(member);
//            em.clear();
//            em.close();

//            //삭제
//            em.remove(member);

//            //1차 캐시에서 조회
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("MemberA");
//
//            //1차 캐시에 저장된다.
//            em.persist(member);
//            //1차 캐시에서 조회
//            Member findMember = em.find(Member.class, 1L);

//            //데이터베이스에서 조회
//            Member findMember2 = em.find(Member.class, 2L);

//            Member findMember = em.find(Member.class, 102L);
//            System.out.println(findMember.getId());
//            System.out.println(findMember.getName());

//            //영속 엔티티의 동일성 보장
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101l);
//            System.out.println(findMember2==findMember1);

//            // 쓰기 지연
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//            //여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.
//
//            tx.commit(); //커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.

//            //Dirty Checking
//            Member member = em.find(Member.class, "memberA");
//
//            //영속 엔티티 데이터 수정
//            member.setName("JPA");
//            tx.commit();

//            //flush
//            Member member = new Member(200L, "member200");
//            em.persist(member);
//            em.flush();  //flush가 일어날때 1차캐시 지워지지 않는다. 오직 변경감지 + 쓰기 지연 SQL 저장소의 SQL들이 DB에 반영된다.
//            System.out.println("=================");

//            //find() 메서드는 플러시가 실행되지 않는다.
//            Member member1 = new Member(300L, "JPA");
//            em.persist(member1);
//            Member member = em.find(Member.class, 1L); //select 쿼리는 즉시 나간다.
//            System.out.println("================");

//            Member member = new Member(1L, "JPA");
//            em.persist(member);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
