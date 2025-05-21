package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Shop;
import umc.study.exception.handler.GeneralHandler;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.web.dto.MissionRequestDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;

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

    public Mission findByMissionId(Long missionId) {
        return missionRepository.findById(missionId).orElseThrow(() -> new GeneralHandler(ErrorStatus.MISSION_NOT_FOUND));
    }
}
