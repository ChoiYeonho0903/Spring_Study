package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain5 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            //연관관계가 없는 방식, 데이터베이스 테이블에 의존적 방식
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("member1");
//            member.setTeamId(team.getId());
//            em.persist(member);
//
//            Member findMember = em.find(Member.class, member.getId());
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);

            //단방향 연관관계
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println(findTeam.getName());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
