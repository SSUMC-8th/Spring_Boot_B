package umc.study.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.*;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.QMissionAssignment;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;
    private final QMissionAssignment missionAssignment = QMissionAssignment.missionAssignment;
    private final QShop shop = QShop.shop;
    private final QRegion region = QRegion.region;

    @Override
    public List<Mission> missionsProgressAndCompleted(Long memberId) {

        return jpaQueryFactory
                .selectFrom(mission)
                .join(missionAssignment).on(mission.id.eq(missionAssignment.mission.id))
                .where(missionAssignment.member.id.eq(memberId))
                .orderBy(missionAssignment.createdAt.desc())
                .offset(0)
                .limit(4)
                .fetch();
    }

    @Override
    public List<Mission> missionsByRegions(String regionName) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (regionName != null) {
            predicate.and(region.name.eq(regionName));
        }

        return jpaQueryFactory
                .selectFrom(mission)
                .join(mission.shop, shop)
                .join(shop.region, region)
                .where(predicate)
                .orderBy(mission.createdAt.desc())
                .offset(0)
                .limit(4)
                .fetch();
    }
}
