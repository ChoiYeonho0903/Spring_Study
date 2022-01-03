package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;


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
