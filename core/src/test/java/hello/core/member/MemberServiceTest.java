package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach // BeforeEach : Test 를 실행전에 무조건 먼저 실행하는것.
    public void beforeEach() {
        AppConfig appConfig = new AppConfig(); // appConfig 에 AppConfig() 객체를 생성해주고, 
        memberService = appConfig.memberService(); // memberService() 객채르 10번줄에 입력
    }


    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);
        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        // then
        Assertions.assertThat(member).isEqualTo(findMember);


    }
}
