package umc.spring.service.memberService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.web.dto.MemberResponseDto;

public interface MemberQueryService {
    Page<Review> getReviewList(Long memberId, Integer page);
    MemberResponseDto.MemberInfoDto getMemberInfo(HttpServletRequest request);
}
