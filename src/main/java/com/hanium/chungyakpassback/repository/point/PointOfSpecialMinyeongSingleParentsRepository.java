package com.hanium.chungyakpassback.repository.point;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.point.PointOfSpecialMinyeongSingleParents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointOfSpecialMinyeongSingleParentsRepository extends JpaRepository<PointOfSpecialMinyeongSingleParents, Long> {
    List<PointOfSpecialMinyeongSingleParents> findAllByUser(User user);
}
