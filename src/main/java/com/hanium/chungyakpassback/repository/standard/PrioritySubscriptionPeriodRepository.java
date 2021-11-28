package com.hanium.chungyakpassback.repository.standard;

import com.hanium.chungyakpassback.entity.standard.PriorityJoinPeriod;
import com.hanium.chungyakpassback.enumtype.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrioritySubscriptionPeriodRepository extends JpaRepository<PriorityJoinPeriod, Long> {
    List<PriorityJoinPeriod> findAllBySupply(Supply supply);
}
