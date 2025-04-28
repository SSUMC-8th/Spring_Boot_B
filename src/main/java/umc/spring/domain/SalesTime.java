package umc.spring.domain;
import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SalesTime extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int dayOfWeek; //(범위 1~7, validation)

    private LocalDateTime openTime;

    private LocalDateTime closeTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

}
