package com.hanium.chungyakpassback.repository.standard;

import com.hanium.chungyakpassback.entity.standard.Income;
import com.hanium.chungyakpassback.enumtype.SpecialSupply;
import com.hanium.chungyakpassback.enumtype.Supply;
import com.hanium.chungyakpassback.enumtype.Yn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    Optional<Income> findBySpecialSupply(SpecialSupply specialSupply);

    Optional<Income> findBySpecialSupplyAndSupplyAndDualIncome(SpecialSupply specialSupply, Supply supply, Yn dualIncome);

    List<Income> findAllBySpecialSupply(SpecialSupply specialSupply);

    List<Income> findAllBySupply(Supply supply);
}
