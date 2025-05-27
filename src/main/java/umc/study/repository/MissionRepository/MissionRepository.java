package umc.study.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Mission;

import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom{
    Optional<Mission> findById(Long id);
    Page<Mission> findAllByShopId(Long shopId, PageRequest pageRequest);
}
