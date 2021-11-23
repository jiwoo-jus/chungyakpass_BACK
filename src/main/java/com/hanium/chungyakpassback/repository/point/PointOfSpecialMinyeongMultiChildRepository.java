package com.hanium.chungyakpassback.repository.point;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.point.PointOfSpecialMinyeongMultiChild;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointOfSpecialMinyeongMultiChildRepository extends JpaRepository<PointOfSpecialMinyeongMultiChild, Long> {

    List<PointOfSpecialMinyeongMultiChild> findAllByUser(User user);
}
