package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Shop;
import umc.study.domain.mapping.MissionAssignment;
import umc.study.exception.handler.GeneralHandler;
import umc.study.repository.MissionAssignmentRepository.MissionAssignmentRepository;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.web.dto.MissionRequestDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionAssignmentRepository missionAssignmentRepository;

    public List<Mission> getAllMissions(Long memberId) {
        List<Mission> filteredMissions = missionRepository.missionsProgressAndCompleted(memberId);

        filteredMissions.forEach(mission -> System.out.println("Missions: " + mission.getName()));

        return filteredMissions;
    }

    public List<Mission> missionsByRegions(String regionName) {
        List<Mission> filteredMissions = missionRepository.missionsByRegions(regionName);

        filteredMissions.forEach(mission -> System.out.println("Missions: " + mission.getName()));

        return filteredMissions;
    }

    public Mission joinMission(MissionRequestDTO.AddMissionDTO request, Shop shop) {
        Mission mission = MissionConverter.toMission(request, shop);

        return missionRepository.save(mission);
    }

    public Mission findMissionByMissionId(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.MISSION_NOT_FOUND));
    }

    public Page<Mission> getMissionListByShopId(Long shopId, Integer page) {
        return missionRepository.findAllByShopId(shopId, PageRequest.of(page, 10));
    }

    public Page<MissionAssignment> getMissionListByMemberId(Long memberId, Integer page) {
        return missionAssignmentRepository.findAllByMemberId(memberId, PageRequest.of(page, 10));
    }
}
