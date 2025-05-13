package umc.study.service.MemberSerivce;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.repository.MemberRepository.MemberRepository;


import static umc.study.domain.QMember.member;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public void myPage(Long memberId) {
        Tuple result = memberRepository.myPage(memberId);

        System.out.println("MyPage: ");

        System.out.println("result = " + result);
    }
}
