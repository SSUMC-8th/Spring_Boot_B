package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.converter.MissionConverter;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Shop;
import umc.study.domain.mapping.MissionAssignment;
import umc.study.service.MemberSerivce.MemberService;
import umc.study.service.MissionAssignmentService;
import umc.study.service.MissionService.MissionService;
import umc.study.validation.annotation.CheckPage;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;
import umc.study.web.dto.MissionResponseDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/members")
public class MemberRestController {

    private final MemberService memberService;
    private final MissionService missionService;
    private final MissionAssignmentService missionAssignmentService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request) {
        Member member = memberService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    //내가 작성한 리뷰 목록 조회
    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 API", description = "사용자 본인의 리뷰 목록을 조회하는 API이며, 페이징을 포함합니다. Query String 으로 Page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreviewListDTO> getMyReviewList(@PathVariable(name = "memberId") Long memberId,
                                                                               @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = memberService.getReviewList(memberId, --page);
        return ApiResponse.onSuccess(ReviewConverter.toReviewPreviewListDTO(reviewList));
    }

    //내가 진행중인 미션 목록 조회
    @GetMapping("/{memberId}/missions")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "사용자 본인이 도전중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. Query String 으로 Page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDTO.MissionAssignmentPreviewListDTO> getMyMissionList(@PathVariable(name = "memberId") Long memberId,
                                                            @CheckPage @Valid @RequestParam(name = "page") Integer page) {
        Page<MissionAssignment> missionList = missionService.getMissionListByMemberId(memberId, --page);
        return ApiResponse.onSuccess(MissionConverter.toMissionAssignmentPreviewListDTO(missionList));
    }

    //진행중인 미션 진행 완료로 바꾸기
    @PostMapping("/{memberId}/missions/{missionId}/complete")
    @Operation(summary = "내가 진행 중인 미션을 미션 진행 완료로 변환 API", description = "사용자가 도전중인 미션을 도전 완료로 변경하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "missionId", description = "도전 완료 요청할 미션 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDTO.MissionCompleteDTO> completeMission(@PathVariable(name = "memberId") Long memberId,
                                                                              @PathVariable(name = "missionId") Long missionId) {
        MissionAssignment missionAssignment = missionAssignmentService.completeMission(memberId, missionId);
        Shop shop = missionService.findMissionByMissionId(missionId).getShop();
        return ApiResponse.onSuccess(MissionConverter.toMissionCompleteDTO(missionAssignment, shop));
    }
}
