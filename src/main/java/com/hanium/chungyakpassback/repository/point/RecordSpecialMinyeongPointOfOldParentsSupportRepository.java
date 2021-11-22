package com.hanium.chungyakpassback.repository.point;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.point.RecordSpecialMinyeongPointOfOldParentsSupport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordSpecialMinyeongPointOfOldParentsSupportRepository extends JpaRepository<RecordSpecialMinyeongPointOfOldParentsSupport, Long> {
    List<RecordSpecialMinyeongPointOfOldParentsSupport> findAllByUser(User user);
}