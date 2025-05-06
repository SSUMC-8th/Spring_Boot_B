package umc.study.domain;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "area_completions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "area_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @Column(name = "completed_at", updatable = false)
    private LocalDateTime completedAt;

    @Column(name = "is_rewarded")
    private boolean isRewarded = false;

    @PrePersist
    protected void onCreate() {
        this.completedAt = LocalDateTime.now();
    }
}