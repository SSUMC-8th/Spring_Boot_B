package umc.study.service.MemberSerivce;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.converter.MemberConverter;
import umc.study.converter.MemberSelectFoodsConverter;
import umc.study.domain.Foods;
import umc.study.domain.Member;
import umc.study.domain.mapping.MemberSelectFoods;
import umc.study.exception.handler.FoodsHandler;
import umc.study.exception.handler.GeneralHandler;
import umc.study.repository.FoodsRepository;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.web.dto.MemberRequestDTO;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static umc.study.domain.QMember.member;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodsRepository foodsRepository;

    public void myPage(Long memberId) {
        Tuple result = memberRepository.myPage(memberId);

        System.out.println("MyPage: ");

        System.out.println("result = " + result);
    }

    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDTO request) {
        Member newMember = MemberConverter.toMember(request);
        List<Foods> foodsList = request.getPreferCategory().stream()
                .map(foods -> {
                    return foodsRepository.findById(foods).orElseThrow(() -> new FoodsHandler(ErrorStatus.FOODS_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberSelectFoods> memberSelectFoodsList = MemberSelectFoodsConverter.toMemberSelectFoodsList(foodsList);

        memberSelectFoodsList.forEach(memberSelectFoods -> {memberSelectFoods.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    public Member findByName(String name) {
        return memberRepository.findByName(name).orElseThrow(() -> new GeneralHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }
}
