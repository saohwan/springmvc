package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository; // final 이 있으면 기본으로 할당을 하던지, 아니면 생성자를 통해서 할당을 해야함.
    private final DiscountPolicy discountPolicy; // DiscountPolicy interface 에만 의존하도록 변경 , [그전에는 = new FixDiscountPolicy 구체 클래스도 의존하여 DIP 위반하고 있었음.]

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // member 저장소에서 memberId 를 조회
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // discountPrice 변수에는 discountPolicy 생성자에 담긴 FixDiscountPolicy 의 discount 할인 금액을 변수로 받아 저장
        return new Order(memberId, itemName, itemPrice, discountPrice);
        // 최종적으로 Order 에는 저장된 값들을 받음.
    }
}
