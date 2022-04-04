package hello.core.member;

import hello.core.AppConfig;
import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    public void join() throws Exception {
        //given (환경)
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when (동작)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then (결과)
        assertThat(member).isEqualTo(findMember);
    }
}
