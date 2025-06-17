package umc.study.service.MemberSerivce;

import com.querydsl.core.Tuple;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.config.security.jwt.JwtTokenProvider;
import umc.study.converter.MemberConverter;
import umc.study.converter.MemberSelectFoodsConverter;
import umc.study.domain.Foods;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.mapping.MemberSelectFoods;
import umc.study.exception.handler.FoodsHandler;
import umc.study.exception.handler.GeneralHandler;
import umc.study.repository.FoodsRepository;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.ReviewRepository;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodsRepository foodsRepository;
    private final ReviewRepository reviewRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void myPage(Long memberId) {
        Tuple result = memberRepository.myPage(memberId);

        System.out.println("MyPage: ");

        System.out.println("result = " + result);
    }

    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDTO request) {
        Member newMember = MemberConverter.toMember(request);
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));

        List<Foods> foodsList = request.getPreferCategory().stream()
                .map(foods -> {
                    return foodsRepository.findById(foods).orElseThrow(() -> new FoodsHandler(ErrorStatus.FOODS_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberSelectFoods> memberSelectFoodsList = MemberSelectFoodsConverter.toMemberSelectFoodsList(foodsList);

        memberSelectFoodsList.forEach(memberSelectFoods -> {memberSelectFoods.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    public Page<Review> getReviewList(Long memberId, Integer page) {
        return reviewRepository.findAllByMemberId(memberId, PageRequest.of(page, 10));
    }

    public MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new GeneralHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                member.getEmail(), null,
                Collections.singleton(() -> member.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication);

        return MemberConverter.toLoginResultDTO(member.getId(), accessToken);
    }

    @Transactional(readOnly = true)
    public MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request) {
        Authentication authentication = jwtTokenProvider.extractAuthentication(request);
        String email = authentication.getName();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return MemberConverter.toMemberInfoDTO(member);
    }
}
