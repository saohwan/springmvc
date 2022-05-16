package hello.core.member;

public class  MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; // 생성자를 통해서 memberRepository 에 무엇이 들어갈지 설정하기 위해 작성.
    }

    @Override
    public void join(Member member) { // join 으로 save 호출시 다형성에 의해서 new MemoryMemberRepository 의 save가 호출 된다.
        memberRepository.save(member); // 구현체가 없으면 Null points inception
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
