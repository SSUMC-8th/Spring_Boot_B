package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.MissionHandler;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.dto.mission.MissionRequestDTO;
import umc.study.repository.MissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Mission createMission(MissionRequestDTO.CreateMissionRequest request) {
        // 1. 가게 조회
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.STORE_NOT_FOUND));

        // 2. 날짜 유효성 검사
        validateMissionDates(request.getStartDate(), request.getEndDate());

        // 3. 중복 미션 검사
        if (missionRepository.existsByStoreAndTitleAndStartDateAndEndDate(
                store, request.getTitle(), request.getStartDate(), request.getEndDate())) {
            throw new MissionHandler(ErrorStatus.MISSION_ALREADY_EXISTS);
        }

        // 4. Mission 생성
        Mission mission = MissionConverter.toMission(request, store);

        // 5. 저장
        return missionRepository.save(mission);
    }

    // 미션 날짜 유효성 검사
    private void validateMissionDates(LocalDate startDate, LocalDate endDate) {
        // 시작일이 현재 날짜 이전인지 확인
        if (startDate.isBefore(LocalDate.now())) {
            throw new MissionHandler(ErrorStatus.MISSION_DATE_INVALID);
        }

        // 종료일이 시작일 이전인지 확인
        if (endDate.isBefore(startDate)) {
            throw new MissionHandler(ErrorStatus.MISSION_DATE_INVALID);
        }
    }
}