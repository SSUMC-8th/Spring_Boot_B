package umc.spring.service.memberService;

import umc.spring.domain.Member;
import umc.spring.web.dto.MemberRequestDto;
import umc.spring.web.dto.MemberResponseDto;

public interface MemberCommandService {
    public Member joinMember(MemberRequestDto.JoinDto request);
    MemberResponseDto.LoginResultDto loginMember(MemberRequestDto.LoginRequestDto request);
}
