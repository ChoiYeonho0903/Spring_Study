package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class JpaMain3 {
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

            //내부조인
//            String query = "select m from Member m join m.team t";
//            List<Member> members = em.createQuery(query, Member.class)
//                    .getResultList();
//            for (Member member : members) {
//                System.out.println(member);
//            }

            //외부조인
//            String query = "SELECT m from Member m LEFT OUTER JOIN m.team t";
//            List<Member> members = em.createQuery(query, Member.class)
//                    .getResultList();
//            for (Member member : members) {
//                System.out.println(member);
//            }

            //컬렉션 조인
//            String query = "SELECT m,t FROM Team t LEFT JOIN t.members m";
//            List<Object[]> result = em.createQuery(query).getResultList();
//            for (Object[] objects : result) {
//                Member member = (Member) objects[0];
//                Team team = (Team) objects[1];
//            }

//            //세타조인
//            String query = "select count(m) from Member m, Team t where m.username = t.name";
//            Object result = em.createQuery(query).getSingleResult();
//            System.out.println(result);

//            //ON 절
//            String query = "SELECT m,t FROM Member m LEFT JOIN m.team t ON t.name='teamA'";
//            List<Object[]> resultList = em.createQuery(query).getResultList();
//            for (Object[] result : resultList) {
//                Member member = (Member) result[0];
//                Team team = (Team) result[1];
//                System.out.println(member);
//                System.out.println(team.getName());
//            }

            //페치 조인
//            String query = "SELECT m FROM Member m JOIN FETCH m.team";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            for (Member member : result) {
//                System.out.println(member);
//                System.out.println(member.getTeam().getName());
//            }

//            String query = "select distinct t from Team t join fetch t.members where t.name='teamA'";
//            List<Team> teams = em.createQuery(query, Team.class).getResultList();
//            for (Team team : teams) {
//                System.out.println(team.getName());
//                for (Member member : team.getMembers()) {
//                    System.out.println(member);
//                }
//            }

//            Query query = em.createQuery("select m.username, m.age from Member m");
//            List<Object[]> resultList = query.getResultList();
//            for (Object[] objects : resultList) {
//                String userName = (String) objects[0];
//                Integer age = (Integer) objects[1];
//                System.out.println(userName);
//                System.out.println(age);
//            }
//            List resultList = query.getResultList();
//            for (Object o : resultList) {
//                Object[] objects = (Object[]) o;
//                String userName = (String) objects[0];
//                Integer age = (Integer) objects[1];
//            }


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
