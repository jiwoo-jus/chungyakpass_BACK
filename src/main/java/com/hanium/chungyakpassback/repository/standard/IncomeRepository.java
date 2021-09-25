package com.hanium.chungyakpassback.repository.standard;

import com.hanium.chungyakpassback.entity.standard.Income;
import com.hanium.chungyakpassback.enumtype.SpecialSupply;
import com.hanium.chungyakpassback.enumtype.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    Optional<Income> findBySpecialSupply(SpecialSupply specialSupply);
    List<Income> findAllBySpecialSupply(SpecialSupply specialSupply);
    List<Income> findAllBySupply(Supply supply);
}
