package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // member 저장소에서 memberId 를 조회
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // discountPrice 변수에는 discountPolicy 생성자에 담긴 FixDiscountPolicy 의 discount 할인 금액을 변수로 받아 저장
        return new Order(memberId, itemName, itemPrice, discountPrice);
        // 최종적으로 Order 에는 저장된 값들을 받음.
    }
}
