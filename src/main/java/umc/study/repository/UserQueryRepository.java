package umc.study.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.JPAExpressions;           // ← 추가
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.*;
import umc.study.domain.enums.OwnerType;
import umc.study.domain.enums.PointStatus;
import umc.study.dto.MyPageDto;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {
    private final JPAQueryFactory qf;
    private final QUser u = QUser.user;
    private final QPhoneVerification pv = QPhoneVerification.phoneVerification;
    private final QPoint pt = QPoint.point;
    private final QImage img = QImage.image;

    public MyPageDto findMyPage(Long userId) {
        return qf
                .select(Projections.constructor(
                        MyPageDto.class,
                        u.id,
                        u.email,
                        u.username,
                        u.nickname,
                        u.gender.stringValue(),
                        u.phoneNumber,
                        new CaseBuilder()
                                .when(pv.isVerified.eq(true)).then(true)
                                .otherwise(false),
                        // 포인트 합계 서브쿼리
                        JPAExpressions.select(pt.amount.sum().coalesce(0))
                                .from(pt)
                                .where(pt.user.id.eq(userId)
                                        .and(pt.status.eq(PointStatus.ACTIVE))),
                        // 프로필 이미지 URL
                        JPAExpressions.select(img.imageUrl)
                                .from(img)
                                .where(img.ownerId.eq(userId)
                                        .and(img.ownerType.eq(OwnerType.USER))
                                        .and(img.isPrimary.eq(true)))
                                .limit(1)
                ))
                .from(u)
                .leftJoin(pv).on(pv.user.id.eq(u.id))
                .where(u.id.eq(userId))
                .fetchOne();
    }
}
