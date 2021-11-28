package com.hanium.chungyakpassback.repository.point;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.point.PointOfGeneralMinyeong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointOfGeneralMinyeongRepository extends JpaRepository<PointOfGeneralMinyeong, Long> {
    List<PointOfGeneralMinyeong> findAllByUser(User user);
}

