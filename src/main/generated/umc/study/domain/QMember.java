package umc.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 543821340L;

    public static final QMember member = new QMember("member1");

    public final umc.study.domain.common.QBaseEntity _super = new umc.study.domain.common.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final ListPath<Alert, QAlert> alertList = this.<Alert, QAlert>createList("alertList", Alert.class, QAlert.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> birthday = createDate("birthday", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<umc.study.domain.enums.Gender> gender = createEnum("gender", umc.study.domain.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<umc.study.domain.mapping.MemberSelectFoods, umc.study.domain.mapping.QMemberSelectFoods> memberSelectFoodsList = this.<umc.study.domain.mapping.MemberSelectFoods, umc.study.domain.mapping.QMemberSelectFoods>createList("memberSelectFoodsList", umc.study.domain.mapping.MemberSelectFoods.class, umc.study.domain.mapping.QMemberSelectFoods.class, PathInits.DIRECT2);

    public final EnumPath<umc.study.domain.enums.MemberStatus> memberStatus = createEnum("memberStatus", umc.study.domain.enums.MemberStatus.class);

    public final ListPath<umc.study.domain.mapping.MissionAssignment, umc.study.domain.mapping.QMissionAssignment> missionAssignmentList = this.<umc.study.domain.mapping.MissionAssignment, umc.study.domain.mapping.QMissionAssignment>createList("missionAssignmentList", umc.study.domain.mapping.MissionAssignment.class, umc.study.domain.mapping.QMissionAssignment.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

