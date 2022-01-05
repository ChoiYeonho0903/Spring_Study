package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    //기간 Period
    @Embedded
    private Period workPeriod;

    //주소
    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();

//    //일대다 단방향
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "MEMBER_ID")
//    private List<AddressEntity> addressHistory = new ArrayList<>();

//    //주소
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name="city", column = @Column(name = "WORK_CITY")),
//            @AttributeOverride(name="street", column = @Column(name = "WORK_STREET")),
//            @AttributeOverride(name="zipcode", column = @Column(name = "WORK_ZIPCODE"))
//    })
//    private Address workAddress;

    @ManyToOne(fetch = FetchType.LAZY) //프록시객체로 조회한다.
    @JoinColumn(name = "TEAM_ID")
    private Team team;

//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;
//
//    //연관관계 편의 메소드
//    public void changeTeam(Team team) {
//        this.team = team;
//        if(!team.getMembers().contains(this)) {
//            team.getMembers().add(this);
//        }
//    }

//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    private Locker locker;

//    @OneToOne(mappedBy = "member")
//    private Locker locker;

//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<Product> products= new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();
}
