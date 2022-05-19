package study.data.jpa.repository;

import java.util.List;
import study.data.jpa.entity.Member;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();

}
