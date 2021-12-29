package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExamMergeMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    public static void main(String[] args) {
        Member member = createMember(240L, "JPA");
        member.setName("JJPA");
        mergeMember(member);
    }

    static Member createMember(Long id, String username) {
        //==영속성 컨텍스트1 시작==/
        EntityManager em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();

        Member member = new Member(id, username);

        em1.persist(member);
        tx1.commit();

        em1.close();

        return member;
    }

    static void mergeMember (Member member) {
        //==영속성 컨텍스트2 시작 ==//
        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();

        tx2.begin();
        Member mergeMember = em2.merge(member);
        tx2.commit();

        //준영속 상태
        System.out.println(member.getName());

        //영속 상태
        System.out.println(mergeMember.getName());

        System.out.println(em2.contains(member));
        System.out.println(em2.contains(mergeMember));

        em2.close();
    }
}
