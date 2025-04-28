package umc.study.domain;

import lombok.*;

import jakarta.persistence.*;
import umc.study.domain.enums.PointReason;
import umc.study.domain.enums.PointStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "points",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "reason", "related_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PointReason reason;

    @Column(name = "related_id")
    private Long relatedId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Enumerated(EnumType.STRING)
    private PointStatus status = PointStatus.ACTIVE;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}