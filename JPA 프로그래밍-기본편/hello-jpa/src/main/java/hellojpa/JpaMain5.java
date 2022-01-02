package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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

//            //단방향 연관관계
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("member1");
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            Member findMember = em.find(Member.class, member.getId());
//            Team findTeam = findMember.getTeam();
//            System.out.println(findTeam.getName());

//            //양방향 연관관계
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("member1");
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            Member findMember = em.find(Member.class, member.getId());
//            List<Member> members = findMember.getTeam().getMembers();
//            for (Member m : members) {
//                System.out.println(m.getName());
//            }

//            // 양방향 연관관계와 연관관계 주인 - 주의
//            Member member = new Member();
//            member.setName("member1");
//            em.persist(member);
//
//            Team team = new Team();
//            team.setName("TeamA");
//            team.getMembers().add(member); //읽기 전용이다. 값을 넣어줘도 DB에 반영되지 않는다.
//            em.persist(team);
//
//            em.flush();
//            em.clear();

//            // 양방향 연관관계와 연관관계 주인 - 맞는 버젼젼
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("member1");
//            member.changeTeam(team);
//            em.persist(member);
//
//            team.getMembers().add(member);
//            //이 코드가 없더라도 출력가능하다. (JPA가 알아서 매핑해준다.)
//            //그러나 flush 없이 실행된는 경우 members 리스트는 비어 있다.
//            //둘다 넣어주는 것이 좋다. test케이스 작성시에 필요
//            //양뱡향 연관관계에서는 양쪽에 값을 넣어주자.
//
//
//            Team findTeam = em.find(Team.class, team.getId()); //1차 캐시
//            List<Member> members = findTeam.getMembers();
//            System.out.println("==================");
//            for (Member m : members) {
//                System.out.println(m.getName());
//            }
//            System.out.println("==================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
