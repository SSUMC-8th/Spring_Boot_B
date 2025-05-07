package umc.study.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMissionAssignment is a Querydsl query type for MissionAssignment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMissionAssignment extends EntityPathBase<MissionAssignment> {

    private static final long serialVersionUID = -1133502057L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMissionAssignment missionAssignment = new QMissionAssignment("missionAssignment");

    public final umc.study.domain.common.QBaseEntity _super = new umc.study.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final umc.study.domain.QMember member;

    public final umc.study.domain.QMission mission;

    public final EnumPath<umc.study.domain.enums.MissionStatus> missionStatus = createEnum("missionStatus", umc.study.domain.enums.MissionStatus.class);

    public final NumberPath<Long> uniqueNumber = createNumber("uniqueNumber", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMissionAssignment(String variable) {
        this(MissionAssignment.class, forVariable(variable), INITS);
    }

    public QMissionAssignment(Path<? extends MissionAssignment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMissionAssignment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMissionAssignment(PathMetadata metadata, PathInits inits) {
        this(MissionAssignment.class, metadata, inits);
    }

    public QMissionAssignment(Class<? extends MissionAssignment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new umc.study.domain.QMember(forProperty("member")) : null;
        this.mission = inits.isInitialized("mission") ? new umc.study.domain.QMission(forProperty("mission"), inits.get("mission")) : null;
    }

}

