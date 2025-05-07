package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSalesTime is a Querydsl query type for SalesTime
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSalesTime extends EntityPathBase<SalesTime> {

    private static final long serialVersionUID = 993979085L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSalesTime salesTime = new QSalesTime("salesTime");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> closeTime = createDateTime("closeTime", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> dayOfWeek = createNumber("dayOfWeek", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> openTime = createDateTime("openTime", java.time.LocalDateTime.class);

    public final QStore store;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QSalesTime(String variable) {
        this(SalesTime.class, forVariable(variable), INITS);
    }

    public QSalesTime(Path<? extends SalesTime> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSalesTime(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSalesTime(PathMetadata metadata, PathInits inits) {
        this(SalesTime.class, metadata, inits);
    }

    public QSalesTime(Class<? extends SalesTime> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

