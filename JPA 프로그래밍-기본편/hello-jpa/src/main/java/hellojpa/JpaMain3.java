package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain3 {
    public static void main(String[] args) {
        //Database 하나당 묶여서 돌아간다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //고객이 요청이 올때마다 생성
        EntityManager em = emf.createEntityManager();
        //JPA data 모든 변경은 transaction 안에서 일어나야 한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            NewMember newMember = new NewMember();
            newMember.setId(1L);
            newMember.setRoleType(RoleType.ADMIN);
            em.persist(newMember);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
