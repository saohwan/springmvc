package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // 실제 동작에 필요한 *구현 객체를 생성* 한다.
    // 객체의 생성과 연결은 AppConfig 가 담당한다.
    // 기획자라 생각하면 쉬움.
    // DIP 완성.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); // MemberServiceImpl 을 만들때 MemoryMemberRepository 객체를 생성 (주입)
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository(); // DB 변경시 이 코드만 변경하면 되는 이점.
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy()); // 2개 의존
        // orderService 를 반환할때 MemoryMemberRepository 객체와 discountPolicy 객체를 생성해서 넣어준다.
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
