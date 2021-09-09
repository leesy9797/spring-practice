package three.etude.service;

import org.springframework.beans.factory.annotation.Autowired;
import three.etude.controller.MemberForm;
import three.etude.domain.Member;
import three.etude.repository.MemberRepository;

public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public String join(Member member) {   //같은 이름이 있는 중복 회원X

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.join(member);
        return member.getM_id();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findById(member.getM_id())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    
    /**
     * 로그인
     */
    public Member login(Member member) {
        Member loginMember = memberRepository.login(member);
        return loginMember;
    }
}

