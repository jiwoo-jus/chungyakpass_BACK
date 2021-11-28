package com.hanium.chungyakpassback.repository.standard;

import com.hanium.chungyakpassback.entity.standard.Income;
import com.hanium.chungyakpassback.enumtype.HousingType;
import com.hanium.chungyakpassback.enumtype.SpecialSupply;
import com.hanium.chungyakpassback.enumtype.Supply;
import com.hanium.chungyakpassback.enumtype.Yn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    Optional<Income> findBySpecialSupplyAndSupplyAndAndHousingType(SpecialSupply specialSupply, Supply supply, HousingType housingType);
    Optional<Income> findBySpecialSupplyAndSupplyAndDualIncomeAndApplicationPublicHousingSpecialLaws(SpecialSupply specialSupply, Supply supply, Yn dualIncome, Yn applicationPublicHousingSpecialLaws);
    List<Income> findAllBySupply(Supply supply);
}
