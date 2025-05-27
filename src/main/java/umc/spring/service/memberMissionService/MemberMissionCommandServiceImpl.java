package umc.spring.service.memberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.MemberMissionHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.MissionRequestDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService{

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Override
    public MemberMission challengeMission(MissionRequestDto.ChallengeMissionDto request){
        MemberMission memberMission = MemberMissionConverter.toMemberMission(request);

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
        memberMission.setMember(member);
        memberMission.setMission(mission);

        return memberMissionRepository.save(memberMission);
    }
    @Override
    public boolean existsByMemberIdAndMissionId(Long memberId, Long missionId){
        return memberMissionRepository.existsByMemberIdAndMissionId(memberId, missionId);
    }

    @Override
    @Transactional
    public MemberMission setMissionComplete(Long missionId, Long memberId) {
        MemberMission memberMission = memberMissionRepository.findByMissionIdAndMemberId(missionId, memberId).orElseThrow(()->new MemberMissionHandler(ErrorStatus.MEMBER_MISSION_NOT_FOUND));
        memberMission.setStatus(MissionStatus.COMPLETE);
        memberMission.setCompletedAt(LocalDateTime.now());
        memberMission.setCertificationNumber(UUID.randomUUID().toString());
        return memberMission;
    }

}
