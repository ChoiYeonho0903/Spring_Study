package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //Database 하나당 묶여서 돌아간다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //고객이 요청이 올때마다 생성
        EntityManager em = emf.createEntityManager();
        //JPA data 모든 변경은 transaction 안에서 일어나야 한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setName("helloA");

            //Create
            em.persist(member);

            //한 건 조회, Read
            Member findMember = em.find(Member.class, 1L);
            System.out.println(findMember.getId()); // 1
            System.out.println(findMember.getName()); // "helloA"

            //Update
            Member updateMember = em.find(Member.class, 1L);
            updateMember.setName("helloJPA");

            //Delete
            Member deleteMember = em.find(Member.class, 1L);
            em.remove(deleteMember);
            
            //목록 조회, Read (JPQL)
            //객체를 대상으로 검색한다.
            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
            for (Member mem : result) {
                System.out.println("member.getName() = " + mem.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
