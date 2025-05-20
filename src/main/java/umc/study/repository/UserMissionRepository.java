package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Mission;
import umc.study.domain.User;
import umc.study.domain.UserMission;

import java.util.List;
import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    boolean existsByUserAndMission(User user, Mission mission);
    Optional<UserMission> findByUserAndMission(User user, Mission mission);
    List<UserMission> findAllByUser(User user);
}