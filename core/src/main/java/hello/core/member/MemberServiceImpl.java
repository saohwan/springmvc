package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 구현체 지정 [DIP 위반]

    @Override
    public void join(Member member) { // join 으로 save 호출시 다형성에 의해서 new MemoryMemberRepository 의 save가 호출 된다.
        memberRepository.save(member); // 구현체가 없으면 Null points inception
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
