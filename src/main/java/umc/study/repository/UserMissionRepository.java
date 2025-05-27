package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.study.domain.Mission;
import umc.study.domain.User;
import umc.study.domain.UserMission;
import umc.study.domain.enums.UserMissionStatus;

import java.util.List;
import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    boolean existsByUserAndMission(User user, Mission mission);
    Optional<UserMission> findByUserAndMission(User user, Mission mission);
    List<UserMission> findAllByUser(User user);

    // 특정 사용자의 진행중인 미션 목록 조회 (페이징)
    @Query("SELECT um FROM UserMission um " +
            "JOIN FETCH um.mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH m.area a " +
            "WHERE um.user = :user AND um.status IN :statuses " +
            "ORDER BY um.createdAt DESC")
    Page<UserMission> findAllByUserAndStatusInOrderByCreatedAtDesc(
            @Param("user") User user,
            @Param("statuses") List<UserMissionStatus> statuses,
            PageRequest pageRequest);

    // 특정 사용자의 모든 미션 목록 조회 (상태 무관)
    @Query("SELECT um FROM UserMission um " +
            "JOIN FETCH um.mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH m.area a " +
            "WHERE um.user = :user " +
            "ORDER BY um.createdAt DESC")
    Page<UserMission> findAllByUserOrderByCreatedAtDesc(
            @Param("user") User user,
            PageRequest pageRequest);
}