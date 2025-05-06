package umc.study.domain;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "area_rewards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaReward {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    @Column(name = "reward_points", nullable = false)
    private Integer rewardPoints = 1000;
}