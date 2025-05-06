package umc.study.domain;

import lombok.*;

import jakarta.persistence.*;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.enums.RewardType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "missions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"store_id", "title", "start_date", "end_date"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "mission_condition", columnDefinition = "TEXT", nullable = false)
    private String condition;

    @Enumerated(EnumType.STRING)
    @Column(name = "reward_type", nullable = false)
    private RewardType rewardType;

    @Column(name = "reward_amount", nullable = false)
    private Integer rewardAmount;

    @Column(name = "unique_code", length = 9, nullable = false, unique = true)
    private String uniqueCode;

    @Column(name = "minimum_purchase_amount")
    private Integer minimumPurchaseAmount;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private MissionStatus status = MissionStatus.ACTIVE;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private Set<UserMission> userMissions = new HashSet<>();

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}