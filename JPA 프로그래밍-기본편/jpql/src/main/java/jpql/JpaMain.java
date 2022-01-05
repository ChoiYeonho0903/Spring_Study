package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setMemberType(MemberType.ADMIN);
            member.changeTeam(team);
            em.persist(member);

//            //TypeQuery와 Query
//            TypedQuery<Member> query1 = em.createQuery("select m from Member as m", Member.class);
//            Query query2 = em.createQuery("select m.username, m.age from Member as m");

            //결과 조회
//            TypedQuery<Member> query = em.createQuery("select m from Member as m", Member.class);
//            List<Member> resultList = query.getResultList();
//            for (Member member1 : resultList) {
//                System.out.println(member1);
//            }
//            TypedQuery<Member> query = em.createQuery("select m from Member as m where m.id = 1L", Member.class);
//            Member result = query.getSingleResult();


//            //파라미터 바인딩
//            Member result = em.createQuery("select m from Member as m where m.username= :username", Member.class)
//                    .setParameter("username", "member1")
//                    .getSingleResult();
//            System.out.println(result.getUsername());
//            List<Member> members = em.createQuery("select m from Member m where m.username = ?1", Member.class)
//                    .setParameter(1, "member1")
//                    .getResultList();
//            for (Member member1 : members) {
//                System.out.println(member1);
//            } //위치기반은 사용하지 말자.

            em.flush();
            em.clear();

//            //프로젝션
//            //엔티티 프로젝션
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .getResultList(); //result 결과값 전부 영속성 컨테스트에서 관리된다.
//            Member findMember = result.get(0);
//            findMember.setAge(20);
//            //update가 되었다 => 영속성 컨텍스트에서 관리한다.

//            //엔티티 프로젝션 (조인)
//            List<Team> result = em.createQuery("select t from Member as m join m.team as t", Team.class)
//                    .getResultList();

//            //임베디드 타입 프로젝션
//            List<Address> result = em.createQuery("select o.address from Order o", Address.class)
//                    .getResultList();

//            //값 타입
//            List<Member> result = em.createQuery("select m.username from Member m", Member.class)
//                    .getResultList();

//            Query 사용
            List resultList = em.createQuery("select  m.username, m.age from Member as m")
                    .getResultList();
            Iterator iterator = resultList.iterator();
            while (iterator.hasNext()) {
                Object[] result = (Object[]) iterator.next();
                String username = (String) result[0];
                Integer age = (Integer) result[1];
            }
//            //TypeQuery 사용
//            List<Object[]> resultList = em.createQuery("select  m.username, m.age from Member as m")
//                    .getResultList();
//            for (Object[] result : resultList) {
//                String username = (String) result[0];
//                Integer age = (Integer) result[1];
//            }

            //new 명령어 조회 => 가장 깔끔.
            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member as m", MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO = result.get(0);
            System.out.println(memberDTO.getUsername());
            System.out.println(memberDTO.getAge());

//            //페이징
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//            System.out.println(result.size());
//            for (Member member1 : result) {
//                System.out.println(member1);
//            }

            //조인
//            List<Member> result = em.createQuery("select m from Member m inner join m.team t", Member.class)
//            .getResultList();
//            List<Member> result = em.createQuery("select m from Member m, Team t where m.username = t.name", Member.class).getResultList();
//            System.out.println(result.size());

//            //서브 쿼리
//            em.createQuery("select m from Member m join Team  t on m")

            //타입 표현 - enum
//            List<Object[]> result = em.createQuery("select m.username, 'HELLO', TRUE from Member m " +
//                    "where m.memberType = jpql.MemberType.ADMIN").getResultList();
//            List<Object[]> result = em.createQuery("select m.username, 'HELLO', TRUE from Member m " +
//                            "where m.memberType = :userType")
//                    .setParameter("userType", MemberType.ADMIN)
//                    .getResultList();

//            for (Object[] objects : result) {
//                System.out.println(objects[0]);
//                System.out.println(objects[1]);
//                System.out.println(objects[2]);
//            }

            //타입 표현 - 엔티티 타입
//            em.createQuery("select i from Item i where type(i) = Book", Item.class).getResultList();

//            //Case식
//            String query =
//                    "select " +
//                            "case when m.age <= 10 then '학생요금'" +
//                            " when m.age >= 60 then '경로요금'" +
//                            "   else '일반요금' " +
//                            "end " +
//                            "from Member m";
//            List<String> result = em.createQuery(query, String.class).getResultList();
//            for (String s : result) {
//                System.out.println(s);
//            }

//            //COALESCE
//            String query = "select coalesce(m.username, '이름 없는 회원') from Member m";
//            List<String> result = em.createQuery(query, String.class).getResultList();
//            for (String s : result) {
//                System.out.println(s);
//            }

//            //함수
//            String query = "select concat('a', 'b') from Member m";
//            List<String> result = em.createQuery(query, String.class).getResultList();
//            for (String s : result) {
//                System.out.println(s);
//            }
            //사용자 정의 함수
//            String query = "select function('group_concat', m.username) from Member m";
//            String query = "select group_concat(m.username) from Member m";
//            List<String> result = em.createQuery(query, String.class).getResultList();
//            for (String s : result) {
//                System.out.println(s);
//            }

//            String query = "select m.username from Member m";
//            String query = "select m.team from Member m"; //묵시적 조인 사용하지 말아야한다.
//            String query = "select t.members from Team t";
//            String query = "select m.username from Team t join t.members m";
//            List result = em.createQuery(query, Collection.class).getResultList();
//            for (Object s : result) {
//                System.out.println(s);
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
