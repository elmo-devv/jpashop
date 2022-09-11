package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@AllArgsConstructor   // 모든 생성자를 만들어준다.
@Service
@Transactional(readOnly = true)  //데이터 변경은 transaction 안에 있어야 한다. readOnly성능 최적화, 읽기용 모드
@RequiredArgsConstructor    //final 이 있는 필드를 가지고 생성자를 만들어준다.
public class MemberService {

//    @Autowired
    private final MemberRepository memberRepository;

//    // 장점 : mock 로 주입 가능, 단점 : applicaiton 로딩 시점에 조립이 다 끝나버린다. 조립 이후에 바꿀 이유가 없음 안좋음
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 장점 : 생성 시점에 조립 끝남, mock 주입 안놓치고 잘 주입 할 수 있다. autowired injection 이 없어도 스프링은 주입 해준다.
    // 위에서 RequiredArgsConstructor 썼기 때문에 필요 없다.
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원가입
     *
     * @param member
     * @return
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicatedMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        //Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
