package umc.study.domain;
import lombok.*;

import jakarta.persistence.*;
import umc.study.domain.enums.AlarmType;

@Entity
@Table(name = "alarm_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlarmSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlarmType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "is_enabled")
    private boolean isEnabled = true;
}