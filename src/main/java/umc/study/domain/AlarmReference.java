package umc.study.domain;

import lombok.*;

import jakarta.persistence.*;
import umc.study.domain.enums.ReferenceType;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "alarm_references")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlarmReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "reference_type", nullable = false)
    private ReferenceType referenceType;

    @Column(name = "reference_id", nullable = false)
    private Long referenceId;

    @OneToMany(mappedBy = "alarmReference", cascade = CascadeType.ALL)
    private Set<Alarm> alarms = new HashSet<>();
}