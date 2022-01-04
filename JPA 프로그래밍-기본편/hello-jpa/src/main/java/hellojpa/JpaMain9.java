package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain9 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //복사해서 사용해야한다.
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
//            Address address = new Address("city", "street", "100");
//            Member member = new Member();
//            member.setName("member1");
//            member.setHomeAddress(address);
//            em.persist(member);
//
//            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
//            Member member2 = new Member();
//            member2.setName("member2");
//            member2.setHomeAddress(copyAddress);
//            em.persist(member2);

//            member.getHomeAddress().setCity("newCity");

//            //setter를 닫아준다. => 바꿔주기
//            Address newAddress = new Address("newCity", address.getStreet(), address.getZipcode());
//            member.setHomeAddress(newAddress);

            //값 타입 컬렉션
            Member member = new Member();
            member.setName("member1");
            member.setHomeAddress(new Address("homeCity", "street", "100"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "100"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "100"));
            em.persist(member); //컬렉션 또한 값타입의 하나이다 값타입의 생명주기는 Member에 의존한다.

            em.flush();
            em.clear();

//            //조회
//            Member findMember = em.find(Member.class, member.getId());
//            //컬렉션 값타입들은 지연로딩이 기본값이다.
//
//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println(address.getCity());
//            }
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println(favoriteFood);
//            }

            Member findMember = em.find(Member.class, member.getId());

//            findMember.getHomeAddress().setCity("newCity"); //update가 되긴하지만 지양해야 한다. 부작용 발생
//            Address homeAddress = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", homeAddress.getStreet(), homeAddress.getZipcode()));
//            //통으로 갈아 껴야한다.

//            //치킨 -> 한식
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");

            //old1 -> newCity
            findMember.getAddressHistory().remove(new AddressEntity("old1", "street", "100"));
            //전체 테이블 삭제
            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street", "100"));
            //insert 두번이 날라간다.
            //old2, newCity 두번 날라간다.
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
