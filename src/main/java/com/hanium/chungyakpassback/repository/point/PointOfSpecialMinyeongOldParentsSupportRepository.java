package com.hanium.chungyakpassback.repository.point;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.point.PointOfSpecialMinyeongOldParentsSupport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointOfSpecialMinyeongOldParentsSupportRepository extends JpaRepository<PointOfSpecialMinyeongOldParentsSupport, Long> {
    List<PointOfSpecialMinyeongOldParentsSupport> findAllByUser(User user);
}