package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("member3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

//            String query = "select m from Member m";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            for (Member member : result) {
//                System.out.println(member.getUsername() + " , " + member.getTeam().getName());
//                //member1, teamA(SQL)
//                //member2, teamA(1차캐시)
//                //member3, teamB(SQL)
//                //N+1 문제 발생
//            }

//            String query = "select m from Member m join fetch m.team"; //한번의 SQL이 날라간다.
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            for (Member member : result) {
//                System.out.println(member.getUsername() + " , " + member.getTeam().getName());
//            }

//            //컬렉션 페치 조인인
//           String query = "select distinct t from Team t join fetch t.members";
//            List<Team> result = em.createQuery(query, Team.class).getResultList();
//            for (Team team : result) {
//                for (Member member : team.getMembers()) {
//                    System.out.println(member);
//
//                }
//            }

//            //Named 쿼리
//            List<Member> result = em.createNamedQuery("Member.findByUsername", Member.class)
//                    .setParameter("username", "member1")
//                    .getResultList();
//            for (Member member : result) {
//                System.out.println(member);
//            }

            //Bulk 연산
            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();
            System.out.println(resultCount);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
