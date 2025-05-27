package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.MemberRequestDto;
import umc.spring.web.dto.MemberResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {
    public static MemberResponseDto.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDto.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDto.JoinDto request){
        Gender gender = switch (request.getGender()){
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            default -> null;
        };
        return Member.builder()
                .address(request.getAddress())
                .gender(gender)
                .name(request.getName())
                .birth(request.getBirth())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .memberFoodList(new ArrayList<>())
                .build();
    }

    public static MemberResponseDto.ReviewPreviewDto reviewPreviewDto(Review review){
        return MemberResponseDto.ReviewPreviewDto.builder()
                .username(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .content(review.getContent())
                .build();
    }
    public static MemberResponseDto.ReviewPreviewListDto reviewPreviewListDto(Page<Review> reviewList){
        List<MemberResponseDto.ReviewPreviewDto> reviewPreviewDtoList = reviewList.stream().
                map(MemberConverter::reviewPreviewDto).collect(Collectors.toList());

        return MemberResponseDto.ReviewPreviewListDto.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreviewDtoList.size())
                .reviewList(reviewPreviewDtoList)
                .build();
    }
}
