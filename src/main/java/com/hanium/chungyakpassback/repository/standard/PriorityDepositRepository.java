package com.hanium.chungyakpassback.repository.standard;


import com.hanium.chungyakpassback.entity.standard.PriorityDeposit;
import com.hanium.chungyakpassback.enumtype.DepositArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriorityDepositRepository extends JpaRepository<PriorityDeposit, Long> {
    List<PriorityDeposit> findByDepositArea(DepositArea depositArea);
}
