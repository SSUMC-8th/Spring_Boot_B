package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.mapping.MissionAssignment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private Integer missionPoint;

    private LocalDateTime expiredAt;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MissionAssignment> missionAssignmentList = new ArrayList<>();
}
