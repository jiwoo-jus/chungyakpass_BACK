package com.hanium.chungyakpassback.repository.standard;

import com.hanium.chungyakpassback.entity.standard.Income;
import com.hanium.chungyakpassback.enumtype.SpecialSupply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    Optional<Income> findBySpecialSupply(SpecialSupply specialSupply);
}
