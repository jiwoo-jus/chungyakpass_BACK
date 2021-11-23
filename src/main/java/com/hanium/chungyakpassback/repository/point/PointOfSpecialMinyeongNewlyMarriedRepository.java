package com.hanium.chungyakpassback.repository.point;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.point.PointOfSpecialMinyeongNewlyMarried;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointOfSpecialMinyeongNewlyMarriedRepository extends JpaRepository<PointOfSpecialMinyeongNewlyMarried, Long> {
    List<PointOfSpecialMinyeongNewlyMarried> findAllByUser(User user);
}
