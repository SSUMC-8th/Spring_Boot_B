package umc.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBusinessHours is a Querydsl query type for BusinessHours
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusinessHours extends EntityPathBase<BusinessHours> {

    private static final long serialVersionUID = 68146541L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusinessHours businessHours = new QBusinessHours("businessHours");

    public final umc.study.domain.common.QBaseEntity _super = new umc.study.domain.common.QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> closingTime = createDateTime("closingTime", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> openingTime = createDateTime("openingTime", java.time.LocalDateTime.class);

    public final QShop shop;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBusinessHours(String variable) {
        this(BusinessHours.class, forVariable(variable), INITS);
    }

    public QBusinessHours(Path<? extends BusinessHours> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBusinessHours(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBusinessHours(PathMetadata metadata, PathInits inits) {
        this(BusinessHours.class, metadata, inits);
    }

    public QBusinessHours(Class<? extends BusinessHours> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shop = inits.isInitialized("shop") ? new QShop(forProperty("shop"), inits.get("shop")) : null;
    }

}

