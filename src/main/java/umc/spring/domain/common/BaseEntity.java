package umc.spring.domain.common;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 직접 테이블로 매핑되지 않고, 상속받는 자식 엔티티에 필드만 물려줌
@EntityListeners(AuditingEntityListener.class) //엔티티의 생명주기를 감지해서 자동으로 날찌를 채워주는 리스너
@Getter
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
