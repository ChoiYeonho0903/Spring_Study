package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain8 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("member");
//            member.setTeam(team);
//            em.persist(member);
//
//            Team teamB = new Team();
//            teamB.setName("teamB");
//            em.persist(teamB);
//
//            Member memberB = new Member();
//            memberB.setName("memberB");
//            memberB.setTeam(teamB);
//            em.persist(memberB);
//
//            em.flush();
//            em.clear();

//            Member findMember = em.find(Member.class, member.getId());

//            Member findMember = em.getReference(Member.class, member.getId()); //프록시 객체 생성
//            System.out.println(findMember.getClass());
//            System.out.println(findMember.getId());
//            System.out.println(findMember.getName()); // 초기화를 통해 실제 Entity가 생성된다.

//            //영속성 컨텍스트에 찾는 엔티티가 있는 경우 엔티티를 반환한다.
//            //혹은 프록시가 이미 있는 경우 프록시를 반환한다.
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println(findMember.getClass());
//
//            Member reference = em.getReference(Member.class, member.getId());
//            System.out.println(reference.getClass());

//            //영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태일 때, 프로시를 초기화하면 문제 발생
//            Member refMember = em.getReference(Member.class, member.getId());
//            System.out.println(refMember.getClass()); //Proxy
//
//            em.detach(refMember);
//
//            refMember.getName();
//            //프록시 초기화 불가능
//            //no Session => 영속성 컨텍스트가 없다는 이야기이다.
//            //LazyInitializationException 예외가 터진다.

//            //프록시 인스턴스의 초기화 여부 확인
//            Member refMember = em.getReference(Member.class, member.getId());
//            System.out.println(emf.getPersistenceUnitUtil().isLoaded(refMember)); //false
//            refMember.getName();
//            System.out.println(emf.getPersistenceUnitUtil().isLoaded(refMember)); //true

//            //프록시 클래스 확인 방법
//            Member refMember = em.getReference(Member.class, member.getId());
//            System.out.println(refMember.getClass().getName());
//
//            //강제초기화
//            Hibernate.initialize(refMember);

//            //지연로딩
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println(findMember.getTeam().getClass());
//            findMember.getTeam().getName(); //프록시 초기화가 일어난다.

//            //N+1 문제 => 최초쿼리 1개 때문에 N개의 쿼리가 나간다.
//            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();
//            //select 문이 두번 날라간다.
//            //SQL : select * from Member
//            //SQL : select * from Team where TEAM_ID

//            //CASCADE
//            Child child1 = new Child();
//            Child child2 = new Child();
//            Parent parent = new Parent();
//            parent.addChild(child1);
//            parent.addChild(child2);
//
////            em.persist(child1);
////            em.persist(child2);
//            em.persist(parent); //cascade 설정하면 child persist 생략가능하다.

            //고아 객체
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);

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
