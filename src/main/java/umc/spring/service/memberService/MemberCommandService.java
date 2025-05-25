package umc.spring.service.memberService;

import umc.spring.domain.Member;
import umc.spring.web.dto.MemberRequestDto;

public interface MemberCommandService {
    public Member joinMember(MemberRequestDto.JoinDto request);
}
