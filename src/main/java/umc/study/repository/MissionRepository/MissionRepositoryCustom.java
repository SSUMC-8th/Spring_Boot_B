package umc.study.repository.MissionRepository;

import umc.study.domain.Mission;

import java.util.List;

public interface MissionRepositoryCustom{
    List<Mission> missionsProgressAndCompleted(Long memberId);
    List<Mission> missionsByRegions(String regionName);
}
