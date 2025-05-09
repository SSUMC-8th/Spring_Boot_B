package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Mission;
import umc.study.repository.MissionRepository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
}
