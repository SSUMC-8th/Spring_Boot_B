package umc.study.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.QMission;
import umc.study.domain.QStore;
import umc.study.domain.QUserMission;
import umc.study.domain.enums.UserMissionStatus;
import umc.study.dto.UserMissionDto;

import java.util.Comparator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserMissionQueryRepository {
    private final JPAQueryFactory qf;
    private final QUserMission um = QUserMission.userMission;
    private final QMission m = QMission.mission;
    private final QStore s = QStore.store;

    public List<UserMissionDto> findUserMissions(Long userId, Long cursorId, int pageSize) {
        return qf
                .select(Projections.constructor(
                        UserMissionDto.class,
                        um.id,
                        m.title,
                        s.name,
                        um.pointsAwarded,
                        um.hasReview,
                        um.status.stringValue(),
                        m.id,
                        s.id
                ))
                .from(um)
                .join(um.mission, m)
                .join(m.store, s)
                .where(
                        um.user.id.eq(userId),
                        um.status.in(
                                UserMissionStatus.IN_PROGRESS,
                                UserMissionStatus.COMPLETED_PENDING,
                                UserMissionStatus.COMPLETED
                        ),
                        cursorId != null ? um.id.lt(cursorId) : null
                )
                .orderBy(um.id.desc())
                .limit(pageSize)
                .fetch();
    }

    public Long findNextCursor(List<UserMissionDto> page) {
        return page.stream()
                .map(UserMissionDto::getUmId)
                .min(Comparator.naturalOrder())
                .orElse(null);
    }
}
