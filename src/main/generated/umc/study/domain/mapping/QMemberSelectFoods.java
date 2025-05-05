package umc.study.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberSelectFoods is a Querydsl query type for MemberSelectFoods
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberSelectFoods extends EntityPathBase<MemberSelectFoods> {

    private static final long serialVersionUID = 955198813L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberSelectFoods memberSelectFoods = new QMemberSelectFoods("memberSelectFoods");

    public final umc.study.domain.common.QBaseEntity _super = new umc.study.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final umc.study.domain.QFoods foods;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final umc.study.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberSelectFoods(String variable) {
        this(MemberSelectFoods.class, forVariable(variable), INITS);
    }

    public QMemberSelectFoods(Path<? extends MemberSelectFoods> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberSelectFoods(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberSelectFoods(PathMetadata metadata, PathInits inits) {
        this(MemberSelectFoods.class, metadata, inits);
    }

    public QMemberSelectFoods(Class<? extends MemberSelectFoods> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foods = inits.isInitialized("foods") ? new umc.study.domain.QFoods(forProperty("foods")) : null;
        this.member = inits.isInitialized("member") ? new umc.study.domain.QMember(forProperty("member")) : null;
    }

}

