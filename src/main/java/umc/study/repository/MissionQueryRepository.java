package umc.study.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.*;
import umc.study.domain.enums.MissionStatus;
import umc.study.dto.AvailableMissionDto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionQueryRepository {
    private final JPAQueryFactory qf;
    private final QMission m = QMission.mission;
    private final QUserMission um = QUserMission.userMission;
    private final QStore s = QStore.store;
    private final QStoreCategory sc = QStoreCategory.storeCategory;

    public List<AvailableMissionDto> findAvailableMissions(
            Long userId, Long areaId, Long cursorId, int pageSize) {

        // 필터링된 미션 ID만
        List<Long> filteredIds = qf
                .select(m.id)
                .from(m)
                .leftJoin(um).on(um.mission.id.eq(m.id).and(um.user.id.eq(userId)))
                .where(
                        m.area.id.eq(areaId),
                        m.status.eq(MissionStatus.ACTIVE),
                        m.startDate.loe(LocalDate.now()),
                        m.endDate.goe(LocalDate.now()),
                        um.id.isNull()
                )
                .fetch();

        // 실제 페이징
        List<AvailableMissionDto> page = qf
                .select(Projections.constructor(
                        AvailableMissionDto.class,
                        m.id,
                        m.title,
                        m.description,
                        m.condition,
                        m.rewardType.stringValue(),
                        m.rewardAmount,
                        m.startDate,
                        m.endDate,
                        m.minimumPurchaseAmount,
                        s.id,
                        s.name,
                        sc.name
                ))
                .from(m)
                .join(m.store, s)
                .join(s.category, sc)
                .where(
                        m.id.in(filteredIds),
                        cursorId == null ? null : m.id.lt(cursorId)
                )
                .orderBy(m.id.desc())
                .limit(pageSize)
                .fetch();

        return page;
    }

    public Long countTotalAvailable(Long userId, Long areaId) {
        return qf
                .select(m.count())
                .from(m)
                .leftJoin(um).on(um.mission.id.eq(m.id).and(um.user.id.eq(userId)))
                .where(
                        m.area.id.eq(areaId),
                        m.status.eq(MissionStatus.ACTIVE),
                        m.startDate.loe(LocalDate.now()),
                        m.endDate.goe(LocalDate.now()),
                        um.id.isNull()
                )
                .fetchOne();
    }

    public Long findNextCursor(List<AvailableMissionDto> page) {
        return page.stream()
                .map(AvailableMissionDto::getMissionId)
                .min(Comparator.naturalOrder())
                .orElse(null);
    }
}
