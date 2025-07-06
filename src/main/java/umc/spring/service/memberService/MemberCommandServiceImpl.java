package umc.spring.service.memberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberFoodConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberFood;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.MemberRequestDto;
import umc.spring.web.dto.MemberResponseDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDto.JoinDto request){
        Member newMember = MemberConverter.toMember(request);
        newMember.encodedPassword(passwordEncoder.encode(request.getPassword())); //비밀번호 암호화 후 저장
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream().
                map(category->{
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberFood> memberFoodList = MemberFoodConverter.toMemberFoodList(foodCategoryList);
        memberFoodList.forEach(memberFood -> {
            memberFood.setMember(newMember);
        });
        log.info("join");
        return memberRepository.save(newMember);
    }

    @Override
    @Transactional
    public MemberResponseDto.LoginResultDto loginMember(MemberRequestDto.LoginRequestDto request) {
        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())){
            throw new MemberHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                member.getEmail(), null, Collections.singleton(()->member.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication);
        return MemberConverter.toLoginResultDto(
                member.getId(),
                accessToken
        );
    }

}
