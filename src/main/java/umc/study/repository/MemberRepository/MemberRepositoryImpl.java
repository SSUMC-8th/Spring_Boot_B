package umc.study.repository.MemberRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.QMember;
import umc.study.domain.QMission;
import umc.study.domain.mapping.QMissionAssignment;
import umc.study.repository.MissionRepository.MissionRepositoryCustom;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;

    @Override
    public Tuple myPage(Long memberId) {

        return jpaQueryFactory
                .select(member.name, member.email, member.point)
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();

    }
}
