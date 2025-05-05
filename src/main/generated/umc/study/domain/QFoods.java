package umc.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFoods is a Querydsl query type for Foods
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoods extends EntityPathBase<Foods> {

    private static final long serialVersionUID = 1951040531L;

    public static final QFoods foods = new QFoods("foods");

    public final umc.study.domain.common.QBaseEntity _super = new umc.study.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QFoods(String variable) {
        super(Foods.class, forVariable(variable));
    }

    public QFoods(Path<? extends Foods> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoods(PathMetadata metadata) {
        super(Foods.class, metadata);
    }

}

