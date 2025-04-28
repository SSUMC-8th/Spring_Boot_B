package umc.study.domain;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "areas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String province;  // 도, 특별시, 광역시

    @Column(nullable = false)
    private String city;      // 시, 군, 구

    @Column(nullable = false)
    private String town;      // 읍, 면, 동

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL)
    private Set<Store> stores = new HashSet<>();

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL)
    private Set<Mission> missions = new HashSet<>();

    @OneToOne(mappedBy = "area", cascade = CascadeType.ALL)
    private AreaReward areaReward;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL)
    private Set<AreaCompletion> areaCompletions = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}