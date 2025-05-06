package umc.study.domain;
import lombok.*;

import jakarta.persistence.*;
import umc.study.domain.enums.OwnerType;

import java.time.LocalDateTime;

@Entity
@Table(name = "images", indexes = {
        @Index(name = "idx_owner_type_id", columnList = "owner_type, owner_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "owner_type", nullable = false)
    private OwnerType ownerType;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "is_primary")
    private boolean isPrimary = false;

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}