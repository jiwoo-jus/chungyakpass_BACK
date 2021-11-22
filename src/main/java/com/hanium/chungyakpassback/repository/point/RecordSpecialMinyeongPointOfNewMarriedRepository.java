package com.hanium.chungyakpassback.repository.point;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.point.RecordSpecialMinyeongPointOfNewMarried;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordSpecialMinyeongPointOfNewMarriedRepository extends JpaRepository<RecordSpecialMinyeongPointOfNewMarried, Long> {
    List<RecordSpecialMinyeongPointOfNewMarried> findAllByUser(User user);
}
