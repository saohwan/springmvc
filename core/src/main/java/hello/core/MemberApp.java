package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        // appConfig 에서 memberService 를 호출하면 MemberServiceImpl 에서 (new MemoryMemberRepository())를 사용한다는 것을 생성하며 주입

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // 모든 AppConfig 정보를 스프링으로 관리하겠다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        // 스프링 컨테이너를 통해 name 은 "memberService"이고 type 은 MemberService다.

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
